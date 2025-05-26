## 📚 Demo Livraria - Spring Boot + MySQL via Docker Compose

Este projeto executa a aplicação `demo-livraria` (imagem hospedada no Docker Hub) conectada a um banco de dados MySQL 8 usando Docker Compose.

### 🐳 Requisitos

- [Docker](https://www.docker.com/) instalado
- Docker Compose instalado (ou Docker Desktop)

------



### 🚀 Como executar (Backend)

1. **Clone ou baixe este repositório:**

https://github.com/afzsilva/demo-livraria.git

> Ou apenas copie os arquivos `docker-compose.yml` e este `README.md`.



2. **Suba os containers:**

Acesse a pasta do projeto via terminal e digite o seguinte comando

```
docker-compose up -d
```

Obs: Quando quiser parar os containers

```
docker-compose down
```



3. **Acesse a aplicação:**

Abra no navegador na seguinte url:

http://localhost:8080/swagger-ui/index.html



------



### 🚀 Como executar (Frontend)

**Clone ou baixe este repositório:**
Leia as instruções no arquivo README no projeto

https://github.com/afzsilva/demo-livraria-front
