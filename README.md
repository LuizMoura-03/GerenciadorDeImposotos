# Gerenciador de Impostos API

O Gerenciador de Impostos API é uma aplicação desenvolvida em Java utilizando Spring Boot para gerenciar diferentes tipos de impostos, como ICMS, ISS, IPI, entre outros. A API permite o registro, consulta, exclusão e cálculo de impostos, além de oferecer autenticação e autorização utilizando Spring Security e JWT (JSON Web Token).

## Funcionalidades
1. Gerenciamento de Tipos de Impostos:

* Listar todos os tipos de impostos disponíveis.
* Cadastrar novos tipos de impostos (nome, descrição e alíquota).
* Obter detalhes de um tipo de imposto específico pelo ID.
* Excluir um tipo de imposto pelo ID.
* 
2. Cálculo de Impostos:

* Calcular o valor do imposto com base no tipo de imposto (identificado pelo ID) e no valor base fornecido.

3. Segurança:

* Implementação de autenticação e autorização utilizando Spring Security e JWT.
* Apenas usuários autenticados podem acessar os endpoints.
* Restringir o acesso a endpoints de criação, exclusão e cálculo de impostos para usuários com o papel de ADMIN.

4.Padrões e Boas Práticas:

* Segue o padrão REST para a construção dos endpoints.
* Utiliza códigos HTTP apropriados para cada operação (200, 201, 204, 400, 404).
* Escalável para adição de novos tipos de impostos e lógica de cálculo no futuro.

## Tecnologias Utilizadas
* Java 17
* Spring Boot 3.4.3
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* H2 Database (para testes)
* PostgreSQL (banco de dados principal)
* Lombok
* JUnit 5 e Mockito (para testes unitários)

## Endpoints da API
    1. Gerenciamento de Impostos

   a) Listar todos os tipos de impostos

   URL: GET /api/impostos/tipos
   Resposta:


[
{
"id": 1,
"name": "ICMS",
"descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
"aliquota": 18.0,
"valorFixoImposto": 0.0
},
{
"id": 2,
"name": "ISS",
"descricao": "Imposto sobre Serviços",
"aliquota": 5.0,
"valorFixoImposto": 0.0
}
]


b) Cadastrar um novo tipo de imposto

URL: POST /api/impostos/tipos
Acesso: Restrito ao papel ADMIN.
Entrada:


{
"name": "IPI",
"descricao": "Imposto sobre Produtos Industrializados",
"aliquota": 12.0,
"valorFixoImposto": 0.0
}
Resposta:


{
"id": 3,
"name": "IPI",
"descricao": "Imposto sobre Produtos Industrializados",
"aliquota": 12.0,
"valorFixoImposto": 0.0
}


c) Obter detalhes de um tipo de imposto pelo ID

URL: GET /api/impostos/tipos/{id}
Resposta:


{
"id": 1,
"name": "ICMS",
"descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
"aliquota": 18.0,
"valorFixoImposto": 0.0
}

d) Excluir um tipo de imposto pelo ID

URL: DELETE /api/impostos/tipos/{id}
Acesso: Restrito ao papel ADMIN.
Resposta: 204 No Content

    2. Cálculo de Impostos
   a) Calcular o valor do imposto
   URL: POST /api/impostos/calculo
   Acesso: Restrito ao papel ADMIN.
   Entrada:


{
"impostoId": 1,
"valorBase": 1000.0
}
Resposta:


{
"nomeImposto": "ICMS",
"valorBase": 1000.0,
"aliquota": 18.0,
"valorTotalImposto": 180.0
}

    3. Gerenciamento de Usuários
   a) Registrar um novo usuário

   URL: POST /api/users/register
   Entrada:


{
"name": "usuario123",
"password": "senhaSegura",
"roles": ["USER"]
}
Resposta:


{
"id": 1,
"name": "usuario123",
"roles": ["USER"]
}

b) Login de usuário

URL: POST /api/users/login
Entrada:


{
"name": "usuario123",
"password": "senhaSegura"
}
Resposta:


{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

## Configuração do Projeto
    1. Banco de Dados
  * H2 Database (para testes)
  * PostgreSQL (produção)
  * Configuração no arquivo application.yml:


spring:
datasource:
url: jdbc:postgresql://localhost:5432/impostosdb
username: postgres
password: 123
driver-class-name: org.postgresql.Driver
jpa:
hibernate:
ddl-auto: update
show-sql: true

    2. JWT
  * Configuração no arquivo application.yml:


jwt:
secret: 3k9J2+7k5f8h1L9m2Pq7Xy8Z0aBcDeFgHiJkLmNoPqRsTuVwXyZ1234567890==
expiration: 1800000 # 30 minutos

## Como Executar o Projeto
    1. Pré-requisitos:

* Java 17+
* Maven
* PostgreSQL

2. Clonar o repositório:



git clone <git@github.com:LuizMoura-03/GerenciadorDeImposotos.git>
cd gerenciadorDeImpostos

    3. Configurar o banco de dados:

* Atualize as credenciais do banco de dados no arquivo application.yml.

      4. Executar o projeto:



* mvn spring-boot:run

      5. Acessar a aplicação:

* A  API estará disponível em: http://localhost:8080

## Testes
    1. Executar os testes:

mvn test

    2. Cobertura de Testes:

* Testes unitários foram implementados utilizando JUnit 5 e Mockito.

