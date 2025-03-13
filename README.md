# Chrono

Chrono é um sistema de gerenciamento de projetos e controle de horas, projetado para ajudar os usuários a rastrear e gerenciar as horas gastas em várias tarefas e projetos. O objetivo é fornecer uma interface intuitiva e uma funcionalidade robusta de backend para gerenciar os dados de tempo relacionados aos projetos.

## Demonstração

Uma versão de deploy está disponível em [https://chrono-steel-six.vercel.app/](https://chrono-steel-six.vercel.app/)

**Nota:** O serviço de demonstração pode estar desabilitado (caso queira testar fique a vontade para entrar em contato comigo via e-mail - eduardohfabri@gmail.com).

## Funcionalidades

- **Controle e gerenciamento das horas** gastas em projetos e tarefas.
- **Organização de tarefas**, com atribuição para membros da equipe e acompanhamento do progresso.
- **Armazenamento e recuperação de registros de tempo** de cada tarefa e projeto.
- **Visualização de dados** sobre o tempo gasto em tarefas e projetos.
- **Autenticação de usuários e controle de acesso**.
- Suporte para adicionar e gerenciar **múltiplos projetos**.

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
- [XAMPP](https://www.apachefriends.org/)

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
4. Caso você decida configurar uma senha no MySQL, altere as configurações no arquivo `application.properties` do projeto para refletir a nova senha.

OBS: Recomenda-se usar o spring.jpa.hibernate.ddl-auto=create para inicializar e após isso comentar a criação das colunas no ChronoApplication.java e enfim colocar o spring.jpa.hibernate.ddl-auto=update, para tornar o banco de dados persistente.

#### 3. Configuração do application.properties

O arquivo `application.properties` do projeto contém configurações importantes para a conexão com o banco de dados e outras funcionalidades:

```properties
spring.application.name=chrono-api

server.port=${PORT:8080}

# Criando ou conectando ao banco de dados localmente
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_gerenciamento?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=

# Criando ou conectando ao banco de dados em produção
# spring.datasource.url=jdbc:mysql://hngomrlb3vfq3jcr.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/tvh4l4220jn8dxna
# spring.datasource.username=s1a99hdal3n36gw4
# spring.datasource.password=ee0bmqjn30z2hpp5
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Tipo de inserção que o JPA faz no banco de dados
spring.jpa.hibernate.ddl-auto=create

# Mostra os dados inseridos no bd dentro do console
spring.jpa.show-sql=true

# Dialeto para não precisar atualizar o bd
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Secret
api.security.token.secret=${JWT_SECRET:my-secret-jwt}
```

**Observações importantes:**
- Para ambiente local, as configurações já estão preparadas para usar o MySQL na porta 3306 sem senha.
- Para produção, existem configurações comentadas que podem ser ativadas quando necessário.
- A propriedade `spring.jpa.hibernate.ddl-auto=create` recria o banco de dados a cada inicialização. Para manter os dados entre reinicializações, altere para `update`.
- O token JWT usa uma chave secreta definida pela variável de ambiente `JWT_SECRET` ou o valor padrão "my-secret-jwt" quando não especificado.

#### 4. Rodar a Aplicação Backend

1. Abra o arquivo principal `ChronoApplication.java` e execute o projeto no seu ambiente de desenvolvimento (IDE como VSCODE, IntelliJ).
2. A aplicação estará disponível para acessar no endereço `localhost:8080` para o backend.

### Documentação da API (Swagger)

A API do Chrono possui documentação interativa através do Swagger. Para acessar:

1. Inicie a aplicação backend
2. Acesse `http://localhost:8080/swagger-ui/index.html` no seu navegador
3. Para usar endpoints protegidos, primeiro autentique-se e copie o token JWT gerado
4. Clique no botão "Authorize" e inclua o token no formato "Bearer {seu-token}"

## Contato

Eduardo Fabri - [eduardohfabri@gmail.com](mailto:eduardohfabri@gmail.com)

Link do Projeto: [https://github.com/eduardofabrii/chrono-api](https://github.com/eduardofabrii/chrono-api)