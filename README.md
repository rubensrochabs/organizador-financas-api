# Sobre o projeto

Este projeto é uma API de organizador financeiro onde é possível cadastrar pessoas e gastos, sendo possível consultar os gastos
por pessoa e intervalo de tempo, e gerar um relatório com valor total e valor percentual de gastos por categoria.

## Tecnologias Utilizadas
### Back end
- Java
- Spring Boot
- JDBC
- Apache Tomcat
- Maven
- MySQL

## Competências utilizadas
- Orientação a Objetos
- Arquitetura em camadas
- Tratamento de exceções
- CRUD - Inserir, alterar, excluir, consultar
- Teste de requisições (Postman)
- Testes unitários (JUnit)

## Como executar o projeto
### Pré-requisitos
- Java 8
- MySQL Database

```bash
# clonar repositório
git clone https://github.com/rubensrochabs/organizador-financas-api

# criar DB no MySQL com o seguinte script
./teste/queries_orgfinanceiro.sql

# configurar no application.properties a conexão do DB. Exemplo:
spring.datasource.url=jdbc:mysql://localhost:3306/orgfinancas
spring.datasource.username=developer
spring.datasource.password=1234567

# executar o projeto
./mvnw spring-boot:run

# para testar as requisições, importe as collections no postman
./teste/workspace.postman_globals.json
./teste/Organizador_Financeiro_API.postman_collection.json
```
