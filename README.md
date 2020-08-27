# SCSCB - Sistema de Controle de Distribuição de Cestas Básicas

## Pré Requisitos
Java 8
MySQL / PostgreSQL / H2

## Configuração
src/main/resources/application.properties
``
spring.profiles.active = [dev/test/prod | Default: test]
``

## Execução
``
cd scdcb-backend-spring
mvn spring-boot:run
``