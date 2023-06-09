Legacy Ink

O Legacy Ink é um sistema para gerenciamento de tatuadores e clientes, permitindo que os usuários visualizem informações sobre os tatuadores cadastrados, como nome, tempo de experiência, avaliação e especialidades, além de permitir a busca por tatuadores com base em suas especialidades.

O sistema também permite que os clientes possam agendar sessões com os tatuadores, informando a data, horário e tipo de tatuagem desejada.

O projeto foi desenvolvido utilizando a linguagem de programação Java e o framework Spring, além de outras bibliotecas como Hibernate, MySQL.
Funcionalidades

    * Cadastro de tatuadores com nome, tempo de experiência, avaliação e especialidades
    * Visualização dos tatuadores cadastrados e suas informações
    * Busca por tatuadores com base em suas especialidades
    * Cadastro de clientes com nome e email
    * Agendamento de sessões com os tatuadores, informando a data, horário e tipo de tatuagem desejada
    * Visualização dos agendamentos realizados

Tecnologias Utilizadas

    Java
    Spring Framework
    Hibernate
    MySQL

Como Executar o Projeto

    Clone o repositório: git clone https://github.com/sadrack404/LegacyInk.git

Crie um banco de dados MySQL e execute o script SQL localizado em /LegacyInk/src/main/resources/sql/create_tables.sql

Abra o arquivo /LegacyInk/src/main/resources/application.properties e configure as propriedades de conexão com o banco de dados (URL, username e password)

    Execute o projeto com o seguinte comando:./mvnw spring-boot:run

    Acesse o sistema no navegador, através da URL:http://localhost:8080/

Considerações Finais

O Legacy Ink é um projeto simples, porém funcional, que pode ser utilizado como base para o desenvolvimento de sistemas mais complexos na área de tatuagens e piercings. O código está disponível em https://github.com/sadrack404/LegacyInk e sugestões e contribuições são sempre bem-vindas.