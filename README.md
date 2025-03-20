# 🎬 CineShare - Compartilhe e Avalie Filmes com Amigos

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-green)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue)](https://www.mysql.com/)

CineShare é uma plataforma onde os usuários podem **compartilhar e avaliar filmes**, criar **grupos de discussão**, interagir em **chats** e receber **notificações sobre suas interações na comunidade**.

---

## 🚀 **Funcionalidades**
✔ Criar e gerenciar **grupos privados** para discutir filmes.  
✔ Avaliar filmes com **notas e comentários**.  
✔ Curtir reviews e comentar nas análises de outros usuários.  
✔ **Chat em tempo real** nos grupos.  
✔ Sistema de **notificações automáticas**.  
✔ **API versionada** para compatibilidade futura.

---

## 🛠 **Tecnologias Utilizadas**
- **Java 21**
- **Spring Boot 3.2.4**
- **Spring Data JPA**
- **Spring Security (JWT - Futuro)**
- **MySQL**
- **Lombok**
- **Docker (Opcional)**

---

## 📌 **Instalação e Execução**

### 1️⃣ **Clone o repositório**
```bash
git clone https://github.com/lucas-morais27/cine-share.git
cd cineshare
```

### 2️⃣ Configure o banco de dados MySQL
Crie um banco de dados no MySQL:

```bash
CREATE DATABASE cineshare;
```

### 3️⃣ Configure o application.properties
Edite o arquivo src/main/resources/application.properties com as credenciais do seu MySQL:

```
spring.datasource.url=jdbc:mysql://localhost:3306/cineshare
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

### 4️⃣ Execute o projeto

```bash
mvn spring-boot:run
```

Agora a API estará rodando em:\
🔗 http://localhost:8080/cineshare/
