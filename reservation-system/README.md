
# **Sistema de Reservas de Restaurante**

Este é um sistema de gerenciamento de reservas de restaurante, desenvolvido com **Spring Boot** e utilizando **H2 Database** como banco de dados em memória. O projeto inclui funcionalidades para criar reservas, consultar todas as reservas e listar mesas disponíveis.

---

## **Índice**
1. [Descrição](#descrição)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Configuração do Projeto](#configuração-do-projeto)
4. [Como Executar](#como-executar)
5. [Estrutura do Projeto](#estrutura-do-projeto)
6. [Endpoints da API](#endpoints-da-api)
7. [Casos de Teste](#casos-de-teste)
8. [Contribuição](#contribuição)
9. [Licença](#licença)

---

## **Descrição**

O sistema permite que os clientes realizem reservas em horários disponíveis, com validações baseadas nas regras de negócio, incluindo:

- **Horário de funcionamento**: 10:00 às 22:00.
- **Limite máximo de pessoas por mesa**: 10.
- **Restrições para datas passadas** ou mesas já ocupadas.

---

## **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot 3.x**
- **H2 Database**
- **Lombok**
- **JUnit 5**
- **Mockito**
- **Maven**

---

## **Configuração do Projeto**

### **Pré-requisitos**

- JDK 17 ou superior
- Maven 3.x
- Um editor de código como **VS Code** ou **IntelliJ IDEA**

### **Instalação**

1. Clone o repositório:
   ```bash
   git clone https://github.com/jeffmqs/restaurant-reservation.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd restaurant-reservation
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```

---

## **Como Executar**

1. Execute o seguinte comando para iniciar o servidor:
   ```bash
   mvn spring-boot:run
   ```
2. Acesse a API no navegador ou em ferramentas como Postman:
   - **URL Base**: [http://localhost:8080](http://localhost:8080)

---

## **Estrutura do Projeto**

```plaintext
restaurant-reservation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.restaurant.reservation_system/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── ReservationSystemApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       ├── integration/
│       ├── system/
│       └── unit/
├── pom.xml
└── README.md
```

---

## **Endpoints da API**

Consulte a [documentação da API](api-documentation.md) para obter detalhes completos sobre os endpoints disponíveis, incluindo exemplos de uso e respostas esperadas.

---

## **Casos de Teste**

Os casos de teste foram desenvolvidos para garantir a funcionalidade correta do sistema e cobrem cenários como:

1. Criação de reservas válidas.
2. Manipulação de erros como datas no passado, horários inválidos, etc.
3. Consultas de reservas e mesas disponíveis.

Consulte a documentação completa dos [casos de teste](test-cases.md) para mais informações.

---

## **Contribuição**

Contribuições são bem-vindas! Siga as etapas abaixo:

1. Faça um fork do repositório.
2. Crie uma nova branch:
   ```bash
   git checkout -b feature/nova-funcionalidade
   ```
3. Faça suas alterações e commit:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Envie suas alterações:
   ```bash
   git push origin feature/nova-funcionalidade
   ```
5. Abra um pull request no repositório original.

---

## **Licença**

Este projeto é licenciado sob a **MIT License**. Consulte o arquivo `LICENSE` para mais informações.