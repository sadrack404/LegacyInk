package br.com.legacyink.domain.service;

import br.com.legacyink.api.domainconverter.TatuadorConvertido;
import br.com.legacyink.api.dto.input.TatuadorInput;
import br.com.legacyink.domain.exception.TatuadorNaoEncontradoException;
import br.com.legacyink.domain.model.Estudio;
import br.com.legacyink.domain.model.Tatuador;
import br.com.legacyink.domain.repository.TatuadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TatuadorService {

    public static final String MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA = "O Tatuador de ID %d não consta no sistema ou não faz parte desse estudio";

    private final TatuadorRepository tatuadorRepository;

    private final EstudioService estudioService;

    private final TatuadorConvertido convertido;

    @Autowired
    public TatuadorService(TatuadorRepository tatuadorRepository, EstudioService estudioService, TatuadorConvertido convertido) {
        this.tatuadorRepository = tatuadorRepository;
        this.estudioService = estudioService;
        this.convertido = convertido;
    }


    public Tatuador buscaTatuadorNoEstudio(Long estudioId, Long tatuadorId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);

        return estudio.getTatuadores().stream()
                .filter(tatuador -> tatuador.getId().equals(tatuadorId))
                .findFirst()
                .orElseThrow(() -> new TatuadorNaoEncontradoException(
                        String.format(MSG_TATUADOR_NAO_CONSTA_NO_SISTEMA, tatuadorId)));
    }

    public List<Tatuador> listarTodosTatuadoresDoEstudio(Long estudioId) {
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        return estudio.getTatuadores();
    }

    @Transactional
    public Tatuador cadastra(Long estudioId, TatuadorInput tatuadorInput) {
        Tatuador tatuador = convertido.paraModelo(tatuadorInput);
        Estudio estudio = estudioService.buscaEstudioOuErro(estudioId);
        estudio.associarTatuador(tatuador);
        return tatuadorRepository.save(tatuador);
    }

    @Transactional
    public Tatuador atualiza(Long estudioId, Long tatuadorId, TatuadorInput tatuadorInput) {
        Tatuador tatuador = this.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        convertido.copiaDTOparaModeloDominio(tatuadorInput, tatuador);
        return tatuadorRepository.save(tatuador);
    }
    @Transactional
    public void ativar(Long estudioId, Long tatuadorId) {
        Tatuador tatuador = this.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        tatuador.ativar();
    }
    @Transactional
    public void inativar(Long estudioId, Long tatuadorId) {
        Tatuador tatuador = this.buscaTatuadorNoEstudio(estudioId, tatuadorId);
        tatuador.inativar();
    }
}
