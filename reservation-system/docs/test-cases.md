# Casos de Teste

Este documento contém os casos de teste para a aplicação de sistema de reservas.

---

## 1. Criar Reserva Válida

- **Objetivo**: Garantir que uma reserva válida seja criada com sucesso.
- **Pré-condições**:
  - O banco de dados deve estar vazio ou com dados que não conflitem.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com o seguinte payload:
     ```json
     {
       "date": "2024-12-01",
       "time": "19:00",
       "numberOfPeople": 4
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 201 (Created).
  - Corpo da resposta contém os detalhes da reserva criada.

---

## 2. Criar Reserva com Mesa Indisponível

- **Objetivo**: Garantir que uma reserva não seja criada quando a mesa já está reservada no mesmo horário.
- **Pré-condições**:
  - Existe uma reserva existente para a mesma data e hora.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com o mesmo payload de uma reserva existente.
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 409 (Conflict).
  - Corpo da resposta contém a mensagem: `Table is not available for the selected date and time.`

---

## 3. Criar Reserva com Data no Passado

- **Objetivo**: Garantir que não seja possível criar uma reserva com data no passado.
- **Pré-condições**:
  - Nenhuma.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com a data anterior ao dia atual:
     ```json
     {
       "date": "2023-01-01",
       "time": "19:00",
       "numberOfPeople": 4
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 400 (Bad Request).
  - Corpo da resposta contém a mensagem: `Date is in the past.`

---

## 4. Criar Reserva com Número de Pessoas Excedendo o Limite

- **Objetivo**: Garantir que o limite máximo de pessoas por reserva seja respeitado.
- **Pré-condições**:
  - O limite de pessoas por mesa é 10.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "2024-12-01",
       "time": "19:00",
       "numberOfPeople": 20
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 400 (Bad Request).
  - Corpo da resposta contém a mensagem: `Number of people exceeds the limit.`

---

## 5. Criar Reserva com Horário Fora do Funcionamento

- **Objetivo**: Garantir que reservas em horários fora do funcionamento do restaurante sejam bloqueadas.
- **Pré-condições**:
  - Horário de funcionamento: das 10h às 22h.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "2024-12-01",
       "time": "03:00",
       "numberOfPeople": 4
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 400 (Bad Request).
  - Corpo da resposta contém a mensagem: `Invalid time. Restaurant is closed.`

---

## 6. Criar Reserva com Número Negativo de Pessoas

- **Objetivo**: Garantir que reservas com valores negativos de pessoas não sejam aceitas.
- **Pré-condições**:
  - Nenhuma.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "2024-12-01",
       "time": "19:00",
       "numberOfPeople": -5
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 400 (Bad Request).
  - Corpo da resposta contém a mensagem: `Number of people must be positive.`

---

## 7. Criar Reserva com Campos Vazios

- **Objetivo**: Garantir que reservas com campos obrigatórios vazios não sejam aceitas.
- **Pré-condições**:
  - Nenhuma.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "",
       "time": "19:00",
       "numberOfPeople": 4
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 400 (Bad Request).
  - Corpo da resposta contém a mensagem: `Date or time cannot be empty.`

---

## 8. Criar Reserva com Horário de Almoço Válido

- **Objetivo**: Garantir que uma reserva em horário de almoço válido seja aceita.
- **Pré-condições**:
  - Horário de almoço: 12h.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "2024-12-01",
       "time": "12:00",
       "numberOfPeople": 4
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 201 (Created).
  - Corpo da resposta contém os detalhes da reserva criada.

---

## 9. Criar Reserva com Número Máximo de Pessoas Permitido

- **Objetivo**: Garantir que uma reserva com exatamente 10 pessoas seja aceita.
- **Pré-condições**:
  - Limite máximo de pessoas: 10.
- **Passos para Execução**:
  1. Enviar uma requisição POST para `/reservations` com:
     ```json
     {
       "date": "2024-12-01",
       "time": "19:00",
       "numberOfPeople": 10
     }
     ```
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 201 (Created).
  - Corpo da resposta contém os detalhes da reserva criada.

---

## 10. Consultar Todas as Reservas

- **Objetivo**: Garantir que todas as reservas possam ser consultadas.
- **Pré-condições**:
  - Deve haver pelo menos uma reserva no banco de dados.
- **Passos para Execução**:
  1. Enviar uma requisição GET para `/reservations`.
  2. Validar a resposta.
- **Resultado Esperado**:
  - Código HTTP 200 (OK).
  - Corpo da resposta contém uma lista de reservas.
