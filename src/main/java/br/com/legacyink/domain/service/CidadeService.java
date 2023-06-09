package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.CidadeConvertido;
import br.com.legacyink.api.dto.input.CidadeInput;
import br.com.legacyink.domain.exception.CidadeNaoEncontradaException;
import br.com.legacyink.domain.model.Cidade;
import br.com.legacyink.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    public static final String MSG_CIDADE_NAO_CONSTA_NO_SISTEMA = "A cidade de ID %d, não consta no sistema";

    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;
    private final CidadeConvertido convertido;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService, CidadeConvertido convertido) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
        this.convertido = convertido;
    }

    public Cidade validaCidadeOuErro(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(
                        String.format(MSG_CIDADE_NAO_CONSTA_NO_SISTEMA, cidadeId)));
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @Transactional
    public Cidade cadastrar(CidadeInput cidadeInput) {
        Cidade cidade = convertido.paraModelo(cidadeInput);
        Long estadoId = cidade.getEstado().getId();

        estadoService.validaEstadoOuErro(estadoId);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade alterar(Long cidadeId, CidadeInput cidadeInput) {
        Cidade cidade = validaCidadeOuErro(cidadeId);
        convertido.copiaDTOparaModeloDominio(cidadeInput, cidade);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void deleta(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        }
    }
}
