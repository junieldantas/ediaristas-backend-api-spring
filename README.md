# Backend E-Diaristas

Projeto de Backend e API desenvolvidos durante a imersão Multi Stack da [TreinaWeb](http://treinaweb.com.br/) utilizando
Java e Spring Boot.

## Dependências do Projeto

- Spring Boot
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- Bean Validation

## Dependências de Desenvolvimento

- Spring Boot Devtools
- Lombok

## Requisitos

- Java 17
- Maven 3.8
- Mysql 8.0

## Executando na máquina local?

Clone este repositório e entre na pasta do projeto.

```sh
git clone https://github.com/junieldantas/ediaristas-backend-api-spring.git
cd e-diaristas-spring
```

Atualize as configurações de acesso ao banco de dados no
arquivo [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/e-diaristas
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Execute o projeto através do Maven.

```sh
mvn spring-boot:run
```

Acesse a aplicação em [http://localhost:8080/admin/diaristas](http://localhost:8080/admin/diaristas).

## Docker - Container Database

Caso utilize docker basta executar o compose, para executar o database mysql

```shell
docker-compose up -d
```
