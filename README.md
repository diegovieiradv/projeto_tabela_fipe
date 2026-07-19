# Tabela FIPE

Aplicacao console Java para consulta de precos de veiculos na Tabela FIPE.

## Descricao

Projeto que consome a API da Tabela FIPE para buscar marcas, modelos, anos e precos de veiculos (carros, motos e caminhoes).

## Funcionalidades

- Selecao de tipo de veiculo (carros, motos, caminhoes)
- Listagem de marcas por tipo
- Listagem de modelos por marca
- Listagem de anos por modelo
- Exibicao de precos de referencia
- Menu interativo em loop

## Tecnologias

- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.5.8 (CommandLineRunner)
- **HTTP Client:** `java.net.http.HttpClient`
- **JSON:** Jackson + Gson
- **Build:** Maven

## Como Rodar

```bash
# Execute com Maven Wrapper
./mvnw spring-boot:run

# Ou build e execute
./mvnw package
java -jar target/projeto_tabela_fipe-0.0.1-SNAPSHOT.jar
```

## API Utilizada

- **Tabela FIPE:** `https://parallelum.com.br/fipe/api/v1/`

## Estrutura

```
src/main/java/.../
├── ProjetoTabelaFipeApplication.java  # Entry point
├── principal/
│   └── Principal.java                 # Menu interativo
├── model/
│   ├── Dados.java                     # Record: codigo, nome
│   └── Veiculos.java                  # Record: valor, marca, modelo
└── service/
    ├── ConsumoApi.java                # HTTP client
    └── ConverteDados.java             # JSON para objeto
```

## Licenca

MIT License - Diego Vieira
