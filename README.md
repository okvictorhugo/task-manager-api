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


git clone https://github.com/okvictorhugo/task-manager-api.git

    Abra o projeto em sua IDE Java (Eclipse, IntelliJ, VS Code).

    Rode o projeto com o Spring Boot (TaskManagerApplication).

    O banco de dados H2 será inicializado automaticamente com as configurações do application.properties.

    Use o Postman ou outra ferramenta para consumir os endpoints.

🔄 Como usar a API com Postman

Abaixo está o passo a passo para utilizar a API de gerenciamento de tarefas:
1. ## 📌 Registro de usuário

    Endpoint: POST /auth/register

    Body (JSON):

{
  "name": "Seu Nome",
  "email": "seu@email.com",
  "password": "123456"
}


![Register](prints/postman-register.png)


2. ## 🔑 Login

    Endpoint: POST /auth/login

    Body (JSON):

{
  "email": "seu@email.com",
  "password": "123456"
}

    ✅ Você receberá um token JWT.


![Login](prints/postman-login.png)

3. ## 📝 Criar nova tarefa

    Endpoint: POST /tasks

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    Body (JSON):

{
  "title": "Exemplo de tarefa",
  "description": "Minha descrição"
}


![Create Task](prints/postman-tasks.png)

4. ## 📋 Listar tarefas

    Endpoint: GET /tasks

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    ✅ Pode incluir filtro: ?status=PENDING


![List Tasks](prints/postman-list-tasks.png)

5. ## ✏️ Atualizar tarefa

    Endpoint: PUT /tasks/{id}

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI

    Body (JSON):

{
  "title": "Título atualizado",
  "description": "Descrição atualizada"
}


![Update Task](prints/postman-update-tasks.png)

6. ## ❌ Deletar tarefa

    Endpoint: DELETE /tasks/{id}

    Headers:
    Authorization: Bearer SEU_TOKEN_AQUI


![Delete Task](prints/postman-delete-tasks.png)

## 📽️ Demonstração em Vídeo

<p align="center">
  <a href="https://youtu.be/uijz55EwAbY">
    <img src="prints/thumb-video.png" alt="Demonstração em Vídeo" width="600">
  </a>
</p>

🔗 [Clique aqui para assistir no YouTube](https://youtu.be/uijz55EwAbY)


📁 Estrutura do projeto

task-manager-api/
│
├── src/
│   ├── main/java/com/victorhugosoares/taskmanager/
│   └── ...
├── prints/
│   ├── postman-register.png
│   ├── postman-login.png
│   ├── postman-tasks.png
│   ├── postman-list-tasks.png
│   ├── postman-update-tasks.png
│   ├── thumb-video.png
│   └── postman-delete-tasks.png
├── 
├── README.md
├── pom.xml
└── .gitignore

## 🧾 Historico de commits
<p align="center"> <img src="prints/git-log.png" alt="Git Log Oneline" width="800"> </p>

✍️ Autor 

Victor Hugo B. Soares
📧 [E-mail - contatovictorhugosoares@gmail.com](contatovictorhugosoares@gmail.com)
🌐 [LinkedIn](https://linkedin.com/in/okvictorhugo)
📞 [Telefone(WhatsApp) +55 11 964628356](https://wa.me/+5511964628356)


# 🇺🇸 📌 Task Manager API

A RESTful API developed with **Spring Boot** for task management with JWT authentication and user registration.

---

## 📚 Features

- User registration
- Login with JWT authentication
- Task creation
- Task listing by user (with status filtering)
- Task updating and deletion
- Route protection with authentication

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Security (JWT)
- Hibernate / JPA
- H2 Database
- Lombok
- Maven

---

## 💻 How to Run Locally

1. Clone the repository:


git clone https://github.com/okvictorhugo/task-manager-api.git

    Open the project in your Java IDE (Eclipse, IntelliJ, or VS Code).

    Run the project using Spring Boot (TaskManagerApplication).

    The H2 database will initialize automatically with the settings from application.properties.

    Use Postman or another API client to consume the endpoints.

🔄 Using the API with Postman

Below is a step-by-step guide for using the Task Manager API:

1. ## 📌 User Registration

    Endpoint: POST /auth/register

    Body (JSON):

{
  "name": "Your Name",
  "email": "your@email.com",
  "password": "123456"
}


![Register](prints/postman-register.png)


2. ## 🔑 Login

    Endpoint: POST /auth/login

    Body (JSON):

{
  "email": "your@email.com",
  "password": "123456"
}

    ✅ You will receive a JWT token.


![Login](prints/postman-login.png)

3. ## 📝 Create New Task

    Endpoint: POST /tasks

    Headers:
    Authorization: Bearer YOUR_TOKEN_HERE

    Body (JSON):

{
  "title": "Sample Task",
  "description": "My description"
}


![Create Task](prints/postman-tasks.png)

4. ## 📋 Listar tarefas

    Endpoint: GET /tasks

    Headers:
    Authorization: Bearer YOUR_TOKEN_HERE

    ✅ Optional Filter: ?status=PENDING


![List Tasks](prints/postman-list-tasks.png)

5. ## ✏️ Update Task

    Endpoint: PUT /tasks/{id}

    Headers:
    Authorization: Bearer YOUR_TOKEN_HERE

    Body (JSON):

{
  "title": "Update title",
  "description": "Updtae description"
}


![Update Task](prints/postman-update-tasks.png)

6. ## ❌ Delete task

    Endpoint: DELETE /tasks/{id}

    Headers:
    Authorization: Bearer YOUR_TOKEN_HERE


![Delete Task](prints/postman-delete-tasks.png)

## 📽️ Video Demo

<p align="center">
  <a href="https://youtu.be/uijz55EwAbY">
    <img src="prints/thumb-video.png" alt="Video Demo" width="600">
  </a>
</p>

🔗 [Clique here to watch on YouTube](https://youtu.be/uijz55EwAbY)


📁 Project Structure

task-manager-api/
│
├── src/
│   ├── main/java/com/victorhugosoares/taskmanager/
│   └── ...
├── prints/
│   ├── postman-register.png
│   ├── postman-login.png
│   ├── postman-tasks.png
│   ├── postman-list-tasks.png
│   ├── postman-update-tasks.png
│   ├── thumb-video.png
│   └── postman-delete-tasks.png
├── 
├── README.md
├── pom.xml
└── .gitignore

## 🧾 Commit History
<p align="center"> <img src="prints/git-log.png" alt="Git Log Oneline" width="800"> </p>

✍️ Author 

Victor Hugo B. Soares
📧 [E-mail - contatovictorhugosoares@gmail.com](contatovictorhugosoares@gmail.com)
🌐 [LinkedIn](https://linkedin.com/in/okvictorhugo)
📞 [Telefone(WhatsApp) +55 11 964628356](https://wa.me/+5511964628356)