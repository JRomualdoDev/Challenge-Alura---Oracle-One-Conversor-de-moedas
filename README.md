# Conversor de Moedas

Este é um aplicativo de console em Java que permite a conversão de valores entre diferentes moedas. Ele utiliza a API da [ExchangeRate-API](https://www.exchangerate-api.com/) para obter as taxas de câmbio mais recentes.

## Tecnologias Utilizadas

*   **Java 17**
*   **Gson:** Para converter objetos Java em sua representação JSON e vice-versa.

## Funcionalidades

*   **Conversão com moedas padrão:** Converta rapidamente de BRL para uma lista de moedas pré-definidas (JPY, USD, ARS, RUB, UAH, MXN).
*   **Conversão com moedas dinâmicas:** Escolha qualquer par de moedas para conversão.
*   **Busca inteligente:** Encontre moedas por código, nome ou país.
*   **Listagem de moedas:** Exiba uma lista de todas as moedas disponíveis.

## Endpoint Utilizado

O projeto utiliza a API da [ExchangeRate-API](https://www.exchangerate-api.com/) para obter as taxas de câmbio. O endpoint utilizado é:

```
https://v6.exchangerate-api.com/v6/YOUR_API_KEY/pair/BASE_CURRENCY/TARGET_CURRENCY
```

## Como usar

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/Challenge-Alura---Oracle-One-Conversor-de-moedas.git
    ```
2.  **Configure a API Key:**
    *   Renomeie o arquivo `config.properties.example` para `config.properties`.
    *   Abra o arquivo `config.properties` e substitua `YOUR_API_KEY_HERE` pela sua chave de API da [ExchangeRate-API](https://www.exchangerate-api.com/).
3.  **Execute a aplicação:**
    *   Abra o projeto em sua IDE de preferência.
    *   Execute a classe `Main`.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

*   `src/br/com/challenge/conversor/`: Contém o código-fonte da aplicação.
    *   `client/`: Responsável pela comunicação com a API.
    *   `models/`: Contém os modelos de dados da aplicação.
    *   `repository/`: Responsável pelo acesso e manipulação dos dados das moedas.
    *   `resources/`: Contém o arquivo `currencies.csv` com a lista de moedas.
    *   `services/`: Contém a lógica de negócio da aplicação.
    *   `utils/`: Contém classes utilitárias.
*   `config.properties.example`: Arquivo de exemplo para configuração da API Key.
*   `README.md`: Este arquivo.

## Melhorias Futuras

Este projeto pode ser expandido e melhorado de várias maneiras. Algumas ideias incluem:

*   **Interface Gráfica (Frontend):**
    *   **Web:** Criar uma interface web com HTML, CSS e JavaScript (usando frameworks como React, Vue ou Angular) que consome a lógica de conversão. O projeto poderia ser hospedado no GitHub Pages.
    *   **Desktop:** Desenvolver uma aplicação desktop com JavaFX ou Swing para uma experiência mais robusta no sistema operacional.

*   **Histórico de Conversões:**
    *   Salvar as conversões realizadas em um arquivo local (CSV, JSON) ou em um banco de dados para consulta futura.

*   **Logs de Eventos:**
    *   Implementar um sistema de logs (com bibliotecas como Log4j ou SLF4J) para registrar eventos importantes da aplicação, como chamadas de API, erros e conversões realizadas. Isso facilitaria a depuração e monitoramento.

*   **Cache de Taxas de Câmbio:**
    *   Implementar um sistema de cache para armazenar as taxas de câmbio por um determinado período. Isso reduziria o número de chamadas à API, melhorando o desempenho e evitando exceder os limites do plano da API.

*   **Mais Testes:**
    *   Aumentar a cobertura de testes, incluindo testes de integração para a camada de serviço e a comunicação com a API (usando mocks).

*   **Empacotamento da Aplicação:**
    *   Criar um executável `.jar` (ou um instalador nativo com jpackage) para facilitar a distribuição e execução do aplicativo sem a necessidade de uma IDE.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para escolher uma das melhorias futuras, abrir uma issue ou enviar um pull request.