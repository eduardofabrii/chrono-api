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

#### 3. Variáveis de ambiente

Credenciais não ficam no repositório. O `application.properties` lê tudo de variáveis de ambiente:

| Variável | Descrição | Exemplo local |
| --- | --- | --- |
| `DB_URL` | URL JDBC do MySQL | `jdbc:mysql://localhost:3306/sistema_gerenciamento?createDatabaseIfNotExist=true` |
| `DB_USERNAME` | Usuário do banco | `root` |
| `DB_PASSWORD` | Senha do banco | *(vazio)* |
| `JWT_SECRET` | Chave usada para assinar os tokens JWT (use um valor longo e aleatório) | `troque-por-um-valor-seguro` |
| `PORT` | Porta HTTP (opcional, padrão `8080`) | `8080` |

Exemplo:

```bash
export DB_URL="jdbc:mysql://localhost:3306/sistema_gerenciamento?createDatabaseIfNotExist=true"
export DB_USERNAME=root
export DB_PASSWORD=
export JWT_SECRET="$(openssl rand -base64 48)"
./mvnw spring-boot:run
```

**Observações importantes:**
- Em produção (Heroku), configure as mesmas variáveis em *Settings → Config Vars*.
- A aplicação não sobe sem `JWT_SECRET`, para evitar tokens assinados com uma chave conhecida.
- `spring.jpa.hibernate.ddl-auto=update` mantém os dados entre reinicializações; use `create` apenas para recriar o banco.

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