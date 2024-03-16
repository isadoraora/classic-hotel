# Classic Hotel API

## Descrição
A Classic Hotel API é uma solução de gestão de hospitalidade baseada na nuvem, projetada para pequenos hotéis e pousadas. A API facilita a gestão de quartos, serviços, clientes e reservas.

## Início Rápido

Para iniciar a aplicação localmente, execute:

```bash
mvn spring-boot:run
```
Para iniciar a aplicação usando Docker, siga as instruções abaixo:

### Docker
Esta aplicação pode ser executada dentro de um container Docker. Para executar a aplicação como um container Docker isolado:

```bash
docker build -t classic-hotel-api

docker run -p 8080:8080 classic-hotel-api
```
### Docker Compose
Para executar a aplicação com todos os serviços relacionados (como um banco de dados), usando o Docker Compose
```bash
docker-compose up --build
```
Isso construirá e iniciará todos os serviços definidos no seu docker-compose.yml. Você pode acessar a aplicação em http://localhost:8080.
## Endpoints da API
### Gestão de Quartos
POST /rooms – Cria um novo quarto

GET /rooms – Lista todos os quartos

GET /rooms/{id} – Recupera detalhes de um quarto específico

PUT /rooms/{id} – Atualiza os detalhes de um quarto específico

DELETE /rooms/{id} – Deleta um quarto específico.

### Gestão de Edifícios (Prédios)
POST /buildings – Cria um novo prédio

GET /buildings – Lista todos os prédios

GET /buildings/{id} – Recupera detalhes de um prédio específico

PUT /buildings/{id} – Atualiza os detalhes de um prédio específico

DELETE /buildings/{id} – Deleta um prédio específico

### Gestão de Clientes
POST /clients – Cadastra um novo cliente

GET /clients – Lista todos os clientes

GET /clients/{id} – Recupera detalhes de um cliente específico

PUT /clients/{id} – Atualiza os detalhes de um cliente específico

DELETE /clients/{id} – Deleta um cliente específico

### Gestão de Reservas
POST /reservations – Cria uma nova reserva

GET /reservations – Lista todas as reservas

GET /reservations/{id} – Recupera detalhes de uma reserva específica

PUT /reservations/{id} – Atualiza uma reserva específica

DELETE /reservations/{id} – Cancela uma reserva específica

### Gestão de Serviços e Opcionais
POST /services – Adiciona um novo serviço ou item opcional

GET /services – Lista todos os serviços e itens opcionais

PUT /services/{id} – Atualiza um serviço ou item opcional específico

DELETE /services/{id} – Remove um serviço ou item opcional específico

### Documentação da API
Acesse a documentação interativa da API e teste os endpoints usando o Swagger UI: [Swagger UI](http://localhost:8080/swagger-ui.html).
