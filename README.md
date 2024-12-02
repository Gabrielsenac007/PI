# 📚 Sistema de Gestão Escolar - Mediotec (Backend)

Este projeto foi desenvolvido como parte de um trabalho acadêmico para um sistema de gestão escolar voltado para o Mediotec, um programa de ensino médio profissionalizante oferecido pelo Senac na região metropolitana do Recife. O backend da aplicação é responsável por gerenciar toda a lógica de negócio, armazenamento de dados e integração com o frontend via APIs RESTful.

---

## 🚀 Tecnologias Utilizadas

- **☕ Java** (JDK 17)  
- **🌱 Spring Framework** (Boot, Data JPA, Security)  
- **📂 Banco de Dados Relacional** (MySQL)  
- **🔐 Autenticação e Autorização** (JWT)  
- **🛠️ Maven** para gerenciamento de dependências  
- **🌐 Integração com APIs RESTful**

---

## 📋 Pré-requisitos

Certifique-se de ter os seguintes softwares instalados antes de começar:

- **Java JDK** (versão 17 ou superior)  
- **Maven** (versão 3.8 ou superior)  
- **MySQL** (ou outro banco de dados relacional configurado)  
- **Docker** (opcional, para deploy containerizado)

---

## 🔧 Instalação e Configuração

1. Clone este repositório:

```
git clone https://github.com/seu-repositorio/backend-mediotec.git
```

2. Acesse o diretório do arquivo:

```
cd backend-mediotec
```

3. Configure o Banco de Dados:

Edite o arquivo **src/main/resources/application.properties** ou **application.yml** com suas credenciais:

```
spring.datasource.url=jdbc:mysql://localhost:3306/mediotec
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

4. Compile e instale as dependências:

```
mvn clean install
```

5. Execute a aplicação:
```
mvn spring-boot:run
```
A aplicação estará disponível em: **http://localhost:8080**.

---

## 📦 Estrutura do Projeto

- **`src/main/java`**: Diretório principal do código-fonte.
  - **`controller`**: Classes responsáveis por gerenciar os endpoints da API REST.
  - **`service`**: Contém as regras de negócio e lógica da aplicação.
  - **`repository`**: Interfaces para interação com o banco de dados utilizando Spring Data JPA.
  - **`entities`**: Entidades que representam os objetos principais do sistema.
- **`src/main/resources`**: Configurações e recursos da aplicação.
  - **`application.properties` ou `application.yml`**: Arquivo para configurar o ambiente da aplicação (ex.: banco de dados, autenticação).
- **`src/test/java`**: Testes unitários e de integração.

---

## 🛠️ Funcionalidades Implementadas

- **Gerenciamento de Usuários**: Cadastro, edição, listagem e exclusão de alunos, professores e coordenadores.
- **Gerenciamento de Turmas**: Criação, edição e atribuição de professores a turmas específicas.
- **Lançamento de Notas**: Professores podem registrar notas e a frequência dos alunos.
- **Relatórios de Desempenho**: Geração de relatórios de desempenho e frequência para coordenadores.
- **Autenticação e Autorização**: Sistema seguro utilizando JWT para controlar o acesso.

---


## 📑 Exemplo de Requisição

- **Endpoint**: `POST /api/users/register/professor`
  - **Body**:
    ```json
    {
      "professor": {
      "cpf": "string",
      "name": "string",
      "email": "string",
      "password": "string"
      },
        "disciplina": [
        {
          "disciplinaId": "string"
        }
      ]
    }
    ```
  - **Resposta**:
    ```
    Professor cadastrado com sucesso!
    ```

---

## 📦 Deploy
O backend pode ser deployado utilizando Docker. Siga os passos:

1. Build da imagem Docker:

```
docker build -t backend-mediotec .
```

2. Inicie o container:
```
docker run -p 8080:8080 backend-mediotec
```

**Raleu! 👍**
