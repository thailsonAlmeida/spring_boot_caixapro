# Projeto caixa eletrônico

## Objetivo
Estudar a aplicação de desing patterns, para insto será utilizado como base as funcionalidades básicas de um caixa eletrônico.

## Tecnologias
* Spring boot
* Banco de dados H2
* Thymeleaf

## Estrutura do Projeto

* Controller: Define os endpoints REST.
* Service: Implementa a lógica de negócio.
* Repository: Acesso ao banco de dados (usando JPA/Hibernate).
* Model: Define as entidades (por exemplo, Conta, Transação).

## Para integrar o padrão Abstract Factory na aplicação Spring Boot do caixa eletrônico
* Definir Interfaces e Classes Concretas para Contas e Usuários
* Definir a Abstract Factory e as Fábricas Concretas
* Modificar o Serviço para Suportar o Padrão Abstract Factory
* Atualizar Controladores e Templates Thymeleaf