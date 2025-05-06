# 📌 Task Manager API

Uma API RESTful desenvolvida com **Spring Boot** para gerenciamento de tarefas com autenticação JWT e cadastro de usuários.

---

## 📚 Funcionalidades

- Registro de novos usuários
- Login com autenticação via JWT
- Criação de tarefas
- Listagem de tarefas por usuário (com filtro por status)
- Atualização e remoção de tarefas
- Proteção de rotas com autenticação

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security (JWT)
- Hibernate / JPA
- H2 Database
- Lombok
- Maven

---

## 💻 Como rodar localmente

1. Clone o repositório:

```bash
git clone https://github.com/okvictorhugo/task-manager-api.git

    Abra o projeto em sua IDE Java (Eclipse, IntelliJ, VS Code).

    Rode o projeto com o Spring Boot (TaskManagerApplication).

    O banco de dados H2 será inicializado automaticamente com as configurações do application.properties.

    Use o Postman ou outra ferramenta para consumir os endpoints.

🔄 Como usar a API com Postman

Abaixo está o passo a passo para utilizar a API de gerenciamento de tarefas:
1. 📌 Registro de usuário

    Endpoint: POST /auth/register

    Body (JSON):

{
  "name": "Seu Nome",
  "email": "seu@email.com",
  "password": "123456"
}

    📷 Exemplo:

2. 🔑 Login

    Endpoint: POST /auth/login

    Body (JSON):

{
  "email": "seu@email.com",
  "password": "123456"
}

    ✅ Você receberá um token JWT.

    📷 Exemplo:

3. 📝 Criar nova tarefa

    Endpoint: POST /tasks

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    Body (JSON):

{
  "title": "Exemplo de tarefa",
  "description": "Minha descrição"
}

    📷 Exemplo:

4. 📋 Listar tarefas

    Endpoint: GET /tasks

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    ✅ Pode incluir filtro: ?status=PENDING

    📷 Exemplo:

5. ✏️ Atualizar tarefa

    Endpoint: PUT /tasks/{id}

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    Body (JSON):

{
  "title": "Título atualizado",
  "description": "Descrição atualizada"
}

    📷 Exemplo:

6. ❌ Deletar tarefa

    Endpoint: DELETE /tasks/{id}

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    📷 Exemplo:

🎥 Demonstração em vídeo

▶️ Clique aqui para assistir ao vídeo da aplicação rodando
📁 Estrutura do projeto

task-manager-api/
│
├── src/
│   ├── main/java/com/victorhugosoares/taskmanager/
│   └── ...
├── prints/
│   ├── register.png
│   ├── login.png
│   ├── create-task.png
│   ├── list-tasks.png
│   ├── update-task.png
│   └── delete-task.png
├── assets/
│   └── demo.mp4
├── README.md
├── pom.xml
└── .gitignore

✍️ Autor

Victor Hugo Barbosa Soares
📧 contatovictorhugosoares@gmail.com
🌐 linkedin.com/in/okvictorhugo