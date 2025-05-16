# Sistema de Agendamento de Transferências Bancárias

<img src="https://img.shields.io/badge/Java-17-blue" alt="Java 17"> <img src="https://img.shields.io/badge/Spring_Boot-3.3.4-brightgreen" alt="Spring Boot 3.3.4"> <img src="https://img.shields.io/badge/H2-2.2.224-orange" alt="H2 Database">

## 📋 Visão Geral
API back-end para agendamento de transferências financeiras .

## ✨ Funcionalidades
- Agendamento de transferências entre contas
- Cálculo automático de taxas conforme tabela regulatória
- Listagem completa de todos os agendamentos
- Documentação interativa via Swagger UI
- Banco de dados em memória (H2) com console web

## 🚀 Tecnologias Principais
| Camada         | Tecnologias                                                                 |
|----------------|-----------------------------------------------------------------------------|
| **Backend**    | Java 17, Spring Boot 3.3.4                                                  |
| **Persistência**| Spring Data JPA, H2 Database                                                |
| **Documentação**| SpringDoc OpenAPI 2.5.0                                                     |
| **Validação**  | Bean Validation 3.0                                                         |
| **Ferramentas**| Maven, Lombok, Postman                                                      |

## 🏗 Arquitetura

## Descrição dos Diretórios

### `src/main/java/bankAPI/`

- **`controller/`**: Responsável pelos endpoints REST da aplicação. Aqui ficam os controladores que recebem e processam as requisições HTTP.
- **`dto/`**: Contém os Data Transfer Objects, que são usados para transferir dados entre diferentes camadas da aplicação (como a camada de controller e a camada de serviço).
- **`enums/`**: Contém as enumerações utilizadas na aplicação, como por exemplo, o `FeeRange` que pode representar diferentes faixas de taxas.
- **`exception/`**: Contém as classes responsáveis pelo tratamento de exceções e erros da aplicação.
- **`model/`**: Contém as entidades JPA (Java Persistence API), que mapeiam as tabelas do banco de dados.
- **`repository/`**: Contém as interfaces Spring Data, que são responsáveis pela comunicação com o banco de dados. Essas interfaces geralmente estendem `JpaRepository` ou outras interfaces fornecidas pelo Spring.
- **`service/`**: Contém a lógica de negócio da aplicação. As classes de serviço implementam as regras de negócio que a aplicação deve seguir.
- **`Application.java`**: Classe principal que inicializa a aplicação Spring Boot.

### `src/main/resources/`

- **`application.properties`**: Arquivo de configuração da aplicação onde são definidos parâmetros de configuração do Spring Boot, como conexão com o banco de dados, portas, etc.

## Considerações Finais

Esta estrutura busca manter o código bem organizado e dividido em camadas, facilitando a manutenção e a escalabilidade. Seguindo essa arquitetura, a aplicação pode ser facilmente expandida com novos recursos sem comprometer a clareza do código.

### Princípios Arquiteturais
- **Clean Architecture**: Separação das camadas
- **SOLID**: Princípios aplicados nos serviços
- **RESTful**: Design de API seguindo boas práticas

## 🔧 Pré-requisitos
- JDK 17+
- Maven 3.9+
- Postman (opcional para testes)

## 🛠 Instalação e Execução

1. **Construção do projeto:**
   ```bash
   mvn clean install

2. **Execução da aplicação:**
   ```bash
   mvn spring-boot:run

## 🚀 Exemplos de Uso da API

### 1. Agendar uma Transferência

**Schedule Transfer:**  
`POST /api/bank/scheduleTransfer =>
{
"sourceAccount": "1111222233",
"destinationAccount": "4444555566",
"amount": 2000,
"transferDate": "2024-12-15"
}
`

**All Transfers:**
`GET /api/bank/allTransfers =>
Response 200 (ok):
[
  {
    "id": 1,
    "sourceAccount": "1111222233",
    "destinationAccount": "4444555566",
    "amount": 2000.00,
    "fee": 12.00,
    "transferDate": "2024-12-15",
    "schedulingDate": "2023-11-24"
  }
]
`
# Licença
```text
MIT License

Copyright (c) 2025 - Fabiano Júnior

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
IN THE SOFTWARE.
```
