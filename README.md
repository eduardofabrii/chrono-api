# Chrono

Chrono é um sistema de gerenciamento de projetos e controle de horas, projetado para ajudar os usuários a rastrear e gerenciar as horas gastas em várias tarefas e projetos. O objetivo é fornecer uma interface intuitiva e uma funcionalidade robusta de backend para gerenciar os dados de tempo relacionados aos projetos.

## Funcionalidades

- Controle e gerenciamento das horas gastas em projetos e tarefas.
- Organize tarefas, atribua-as aos membros da equipe e acompanhe o progresso.
- Armazene e recupere registros de tempo de cada tarefa e projeto.
- Visualização de dados sobre o tempo gasto em tarefas e projetos.
- Autenticação de usuários e controle de acesso.
- Suporte para adicionar e gerenciar múltiplos projetos.

## Tecnologias

Este projeto utiliza as seguintes tecnologias:

- **Frontend:** Angular
- **Backend:** Java Spring Boot
- **Banco de Dados:** MySQL (SQL)
- **Framework de Mapeamento:** MapStruct (para mapeamento entre DTOs e entidades)

## Instalação

Siga os passos abaixo para rodar o Chrono localmente:

### Pré-requisitos

Certifique-se de que você tem as seguintes ferramentas instaladas:

- [Java 21](https://openjdk.java.net/)
- [Node.js e npm](https://nodejs.org/)
- [MySQL](https://dev.mysql.com/downloads/installer/)

### Configuração do Backend

#### 1. Clonar o Repositório

Primeiro, clone o repositório do **Chrono API** para o seu ambiente local.

```bash
git clone https://github.com/eduardofabrii/chrono-api.git
cd chrono-api
```

#### 2. Configurar o Banco de Dados

Para configurar o banco de dados, você precisará do XAMPP com o MySQL rodando na porta 3306 e sem senha configurada.

1. Instale o XAMPP (caso não tenha feito isso ainda) a partir de [https://www.apachefriends.org/](https://www.apachefriends.org/).
2. Abra o XAMPP e inicie o MySQL na porta 3306.
3. Após iniciar o MySQL, garanta que a conexão não possui senha. Se necessário, remova qualquer senha configurada ou deixe em branco.
(Caso queira colocar senha é obrigatório alterar as configurações no application.properties)

#### 2. Rodar a aplicação

Para configurar o banco de dados, você precisará do XAMPP com o MySQL rodando na porta 3306 e sem senha configurada.

1. Abra o ChronoApplication e seja feliz :D

AGUARDE OS PRÓXIMOS PASSOS!!!!!