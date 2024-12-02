# Documentação da API de Reservas de Restaurante

Este documento descreve os endpoints disponíveis na API do sistema de reservas de restaurante, incluindo detalhes sobre as requisições, respostas e exemplos.

---

## **Base URL**

A API está disponível na seguinte URL base:

http://localhost:8080


---

## **Endpoints**

### 1. Criar Reserva

- **Endpoint**: `/reservations`
- **Método HTTP**: `POST`
- **Descrição**: Cria uma nova reserva no sistema.
- **Requisição**:
  - **Headers**: `Content-Type: application/json`
  - **Body**:
    ```json
    {
      "date": "YYYY-MM-DD",
      "time": "HH:mm",
      "numberOfPeople": 4
    }
    ```
- **Respostas**:
  - **201 Created**:
    ```json
    {
      "id": 1,
      "date": "2024-12-01",
      "time": "19:00",
      "numberOfPeople": 4
    }
    ```
  - **400 Bad Request**: Quando algum campo obrigatório está vazio ou contém valores inválidos.
    ```json
    {
      "error": "Invalid time. Restaurant is closed."
    }
    ```
  - **409 Conflict**: Quando já existe uma reserva para a mesma data e horário.
    ```json
    {
      "error": "Table is not available for the selected date and time."
    }
    ```

---

### 2. Consultar Todas as Reservas

- **Endpoint**: `/reservations`
- **Método HTTP**: `GET`
- **Descrição**: Retorna todas as reservas cadastradas no sistema.
- **Requisição**:
  - **Headers**: Nenhum
  - **Body**: Nenhum
- **Respostas**:
  - **200 OK**:
    ```json
    [
      {
        "id": 1,
        "date": "2024-12-01",
        "time": "19:00",
        "numberOfPeople": 4
      },
      {
        "id": 2,
        "date": "2024-12-02",
        "time": "12:00",
        "numberOfPeople": 2
      }
    ]
    ```

---

### 3. Consultar Mesas Disponíveis

- **Endpoint**: `/api/tables`
- **Método HTTP**: `GET`
- **Descrição**: Retorna todas as mesas disponíveis no restaurante.
- **Requisição**:
  - **Headers**: Nenhum
  - **Body**: Nenhum
- **Respostas**:
  - **200 OK**:
    ```json
    [
      {
        "id": 1,
        "capacity": 4,
        "reserved": false
      },
      {
        "id": 2,
        "capacity": 6,
        "reserved": false
      }
    ]
    ```

---

### **Erros Comuns**

#### **400 Bad Request**

- **Descrição**: Ocorre quando a requisição contém dados inválidos ou incompletos.
- **Exemplo**:
  ```json
  {
    "error": "Date is in the past."
  }

## **Erros Comuns**

### **409 Conflict**
- **Descrição**: Ocorre quando há um conflito, como tentar reservar uma mesa já ocupada.
- **Exemplo**:
  ```json
  {
    "error": "Table is not available for the selected date and time."
  }
  ```

### **500 Internal Server Error**
- **Descrição**: Ocorre quando há um erro inesperado no servidor.

---

## **Regras de Negócio**

### **Horário de Funcionamento**
- Restaurante funciona entre **10:00** e **22:00**.
- Reservas fora deste horário são rejeitadas.

### **Limite de Pessoas por Mesa**
- O limite máximo é de **10 pessoas por mesa**.

### **Validação de Data**
- Reservas para datas passadas são rejeitadas.

### **Mesas Indisponíveis**
- Reservas para horários já ocupados não são permitidas.

---

## **Exemplos de Uso**

### **Criar Reserva**

- **Requisição**:
  ```bash
  curl -X POST http://localhost:8080/reservations \
  -H "Content-Type: application/json" \
  -d '{
    "date": "2024-12-01",
    "time": "19:00",
    "numberOfPeople": 4
  }'
  ```

- **Resposta**:
  ```json
  {
    "id": 1,
    "date": "2024-12-01",
    "time": "19:00",
    "numberOfPeople": 4
  }
  ```

---

### **Consultar Todas as Reservas**

- **Requisição**:
  ```bash
  curl -X GET http://localhost:8080/reservations
  ```

- **Resposta**:
  ```json
  [
    {
      "id": 1,
      "date": "2024-12-01",
      "time": "19:00",
      "numberOfPeople": 4
    }
  ]
  ```

---

### **Consultar Mesas Disponíveis**

- **Requisição**:
  ```bash
  curl -X GET http://localhost:8080/api/tables
  ```

- **Resposta**:
  ```json
  [
    {
      "id": 1,
      "capacity": 4,
      "reserved": false
    }
  ]
  ```
