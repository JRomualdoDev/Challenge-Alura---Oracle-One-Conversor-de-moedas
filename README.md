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

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.
