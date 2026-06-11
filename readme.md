# Raízes do Nordeste API

API REST desenvolvida com Spring Boot para gerenciamento de pedidos, produtos, estoque, unidades, pagamentos simulados, programa de fidelidade, autenticação JWT e auditoria.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- PostgreSQL
- Flyway
- Lombok
- Swagger / OpenAPI
- Maven

---

# Pré-requisitos

Antes de executar o projeto, certifique-se de possuir instalado:

- Java 21+
- Maven 3.9+
- PostgreSQL
- Git

---

# Clonando o Projeto

```bash
git clone <url-do-repositorio>
cd api
```

---

# Configuração do Banco de Dados

Crie um banco PostgreSQL:

```sql
CREATE DATABASE raizes_do_nordeste;
```

Configure as credenciais no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/raizes_do_nordeste
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```

---

# Executando as Migrations

As migrations serão executadas automaticamente pelo Flyway ao iniciar a aplicação.

Caso necessário:

```bash
./mvnw flyway:migrate
```

---

# Executando a Aplicação

Linux / MacOS

```bash
./mvnw spring-boot:run
```

Windows

```bash
mvnw.cmd spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

---

# Usuário Administrador

Ao iniciar a aplicação pela primeira vez, um usuário administrador é criado automaticamente.

Credenciais padrão:

```text
Email: admin@raizes.com
Senha: admin123
```

---

# Documentação Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui/index.html
```

Especificação OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

# Autenticação

Realize login através do endpoint:

```http
POST /auth/login
```

Exemplo:

```json
{
  "email": "admin@raizes.com",
  "senha": "admin123"
}
```

Resposta:

```json
{
  "token": "jwt-token"
}
```

Copie o token retornado.

No Swagger:

1. Clique em "Authorize".
2. Informe:

```text
Bearer SEU_TOKEN
```

3. Clique em Authorize.

---

# Perfis de Acesso

## ADMIN

Permissões:

- Gerenciar usuários
- Gerenciar produtos
- Gerenciar unidades
- Gerenciar estoques
- Consultar auditorias
- Consultar fidelidade
- Atualizar status de pedidos

## ATENDENTE

Permissões:

- Gerenciar produtos
- Gerenciar estoques
- Consultar fidelidade
- Atualizar status de pedidos

## CLIENTE

Permissões:

- Criar pedidos
- Realizar pagamentos
- Cancelar pedidos
- Consultar pontos de fidelidade

---

# Fluxo de Testes

## 1. Registrar usuário

```http
POST /auth/register
```

---

## 2. Realizar login

```http
POST /auth/login
```

---

## 3. Criar unidade

```http
POST /unidades
```

Necessário usuário ADMIN.

---

## 4. Criar produto

```http
POST /produtos
```

Necessário ADMIN ou ATENDENTE.

---

## 5. Criar estoque

```http
POST /estoques
```

Necessário ADMIN ou ATENDENTE.

---

## 6. Criar pedido

```http
POST /pedidos
```

O pedido será criado com status:

```text
AGUARDANDO_PAGAMENTO
```

---

## 7. Processar pagamento

```http
POST /pedidos/{id}/pagamento
```

Possíveis resultados:

```text
PAGO
```

ou

```text
CANCELADO
```

---

## 8. Atualizar status

```http
PATCH /pedidos/{id}/status
```

Fluxo esperado:

```text
PAGO
↓
EM_PREPARO
↓
PRONTO
↓
ENTREGUE
```

---

## 9. Cancelar pedido

```http
PATCH /pedidos/{id}/cancelar
```

Ao cancelar:

- Estoque é devolvido.
- Pedido recebe status CANCELADO.

---

## 10. Consultar fidelidade

Cliente:

```http
GET /fidelidade/meus-pontos
```

Administrador/Atendente:

```http
GET /fidelidade/{id}
```

---

# Collection Postman

Uma Collection do Postman foi disponibilizada para facilitar os testes da aplicação.

Localização:

```text
/postman
```

Importe o arquivo da Collection no Postman para executar os testes rapidamente.

---

# Tratamento de Erros

A API retorna respostas padronizadas para erros:

Exemplo:

```json
{
  "timestamp": "2026-06-10T19:00:00",
  "status": 404,
  "message": "Produto não encontrado"
}
```

Principais códigos:

| Código | Descrição        |
| ------ | ---------------- |
| 200    | Sucesso          |
| 201    | Criado           |
| 204    | Sem conteúdo     |
| 400    | Regra de negócio |
| 401    | Não autenticado  |
| 403    | Sem permissão    |
| 404    | Não encontrado   |
| 500    | Erro interno     |

---

# Auditoria

O sistema registra operações relevantes como:

- Login
- Cadastro de usuários
- Criação de pedidos
- Pagamentos
- Cancelamentos
- Alterações de status
- Operações administrativas

Os registros ficam armazenados para rastreabilidade e consulta administrativa.

---

# Autor

Projeto desenvolvido para fins acadêmicos na disciplina de Desenvolvimento Back-End.
