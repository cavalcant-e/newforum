# Fórum Hub

  

## Descrição

  

Fórum Hub é um desafio do programa ONE (Oracle Next Education). Este projeto visa colocar em praticas os temas abordados durante o curso.

  

## Funcionalidades
 

-  **Criação de Tópicos:** Usuários cadastrados podem criar novos tópicos contendo um título e uma mensagem.
 
-  **Listagem de Tópicos:** Possibilidade de listar todos os tópicos cadastrados no banco de dados.

-  **Visualização de Tópico:** Visualizar os detalhes de um tópico específico.

-  **Autenticação e Autorização:** Somente usuários autenticados podem criar, editar ou deletar tópicos, garantindo a segurança e integridade dos dados.

-  **Criação de Respostas:** Usuários cadastrados podem criar novas respostas para tópicos contendo uma mensagem.

-  **Listagem de Respostas:** Possibilidade de listar todos as respostas de tópicos cadastrados no banco de dados.

-  **Visualização de Tópico:** Visualizar os detalhes de uma resposta específico.

-  **Autenticação e Autorização:** Somente usuários autenticados podem criar, editar ou deletar respostas, garantindo a segurança e integridade dos dados.

-  **Registro de Usuários:** Registro de novos usuários com geração de token JWT para autenticação.

-  **Listagem somente dos tópicos respondidos:** Possibilidade de listar todos os topicos que foram respondidos.

-  **Desativar topico:** Possibilidade de fechar o topico.

  

## Requisitos de Negócio

  

1.  **Usuários Cadastrados:** Apenas usuários registrados podem criar, editar ou deletar tópicos e respostas.

2.  **Autenticação:** Utilização de tokens Bearer para autenticação nas requisições.

3.  **Segurança:** Apenas o autor do tópico ou resposta pode editá-lo ou deletá-lo.

4.  **Estrutura do Tópico:** Cada tópico deve ter um título, uma mensagem, pertencer a um curso e ter uma data de criação gerada automaticamente.

  

## Tecnologias Utilizadas

  

-  **Java 17**

-  **Spring Boot 3.3.0**

-  **MySQL**

-  **Hibernate**

-  **JWT (JSON Web Token)**

-  **Lombok**

-  **Flyway**

-  **Spring Security**

-  **Spring Data JPA**

    

## Endpoints Principais e Estruturas Json

  
-  **Cadastrar Usuário:**  `POST /usuario`
{
    "nome":"Joao",
    "email": "joao@joao.com",
    "senha":"654321"
}
-  **Autenticar Usuário:**  `POST /login`
{
    "email":"joao@joao.com",
   "senha": "654321"
}
-  **Criar Tópico:**  `POST /topico`
{
  "titulo": "teste",
  "mensagem": "teste usuario 2",
    "usuarioid":1
}
-  **Listar Tópicos Ativos:**  `GET/topico/ativo`
-  **Listar Tópicos Respondidos:**  `GET/topico/respondidos`
-  **Listar Tópicos Sem Respostas:**  `GET/topico/semresposta`
-  **Visualizar um Tópico Específico:**  `GET /topico/{id}`
-  **Editar Tópico:**  `PUT /topico`
{
"id":19,
"titulo": "Segundo topicoxx",
"mensagem":"Segundo MensagemxY",
"usuarioid":1
}
-  **Deletar Tópico:**  `DELETE /topico/{id}`
-  **Fechar Tópico:**  `DELETE /topico/fechar`
 {
"topicoid":20,
"usuarioid":2
}
-  **Responder tópico:**  `POST /resposta`
{
  "topicoid": 7,
  "solucao" : "ctrl shift del",
  "usuarioid":2
}
-  **Listar Respostas de um Tópico:**  `GET /resposta/5`
-  **Listar Todas as Respostas:**  `GET /resposta`
-  **Visualizar Respostas de um Tópico Específico:**  `GET /topicos/{id}/respostas/{id2}`


-  **Editar Resposta:**  `PUT /resposta`
{
"topicoid":5,
"respostaid":3,
"solucao":"reinicializar",
"usuarioid":2
}
