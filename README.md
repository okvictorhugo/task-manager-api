# Task Manager API

API RESTful desenvolvida em Java com Spring Boot para gerenciamento de tarefas pessoais.  
Permite criar, listar (com filtros), atualizar e excluir tarefas vinculadas a usuários autenticados.

## 🚀 Funcionalidades

- ✅ Criar tarefas com título e descrição
- 📋 Listar todas as tarefas do usuário
- 🔍 Filtrar tarefas por status (`PENDING`, `IN_PROGRESS`, `COMPLETED`)
- ✏️ Atualizar título, descrição e status da tarefa
- ❌ Excluir tarefa
- 🔐 Acesso autenticado por usuário

## 🛠 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security (Autenticação)
- H2 Database (ambiente de desenvolvimento)
- Lombok
- Jakarta Validation
- Maven

## 📦 Estrutura de Diretórios

src/
├── main/
│ ├── java/
│ │ └── com/victorhugosoares/taskmanager/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── entity/
│ │ ├── mapper/
│ │ ├── repository/
│ │ ├── service/
│ │ └── config/ (opcional)
│ └── resources/
│ └── application.properties


## 🧪 Como testar

1. Clone o repositório:
   ```bash
   git clone https://github.com/okvictorhugo/task-manager-api.git
   cd task-manager-api

    Importe no Eclipse, IntelliJ ou qualquer IDE com suporte a Maven.

    Rode o projeto via TaskManagerApplication.

    Teste os endpoints via Postman ou Insomnia:

        POST /tasks

        GET /tasks

        PUT /tasks/{id}

        DELETE /tasks/{id}

    Certifique-se de autenticar o usuário para utilizar os endpoints protegidos (se JWT estiver implementado).

📸 Apresentação

    Prints dos testes no Postman: ./docs/prints/

    Vídeo da API em funcionamento: [link aqui quando disponível]

👨‍💻 Autor

Victor Hugo Barbosa Soares
📧 contatovictorhugosoares@gmail.com
🔗 LinkedIn