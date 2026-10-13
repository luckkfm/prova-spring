# Leque de Vagas API

API REST do Leque de Vagas, feita em Spring Boot para a disciplina de Introdução ao Spring Boot. Ela guarda as vagas, as empresas que publicam essas vagas e as pessoas cadastradas. O front-end que consome os dados é o [lequedevagas-web](https://github.com/Um-Leque-de-Tecnologia/lequedevagas-web).

Este README cobre a entrega da 1ª etapa: CRUD completo, DTOs, validação, tratamento de erros, camadas separadas e entidades JPA com banco H2.

## Tecnologias

- Java 21
- Spring Boot 4.1.1 (Web MVC, Validation, Data JPA)
- H2 em memória
- Maven Wrapper

## Como rodar

Precisa ter o JDK 21 instalado.

```bash
./mvnw spring-boot:run     # Linux e macOS
mvnw.cmd spring-boot:run   # Windows
```

A API sobe em `http://localhost:8081`.

O banco é criado do zero toda vez que a aplicação sobe, e o arquivo `src/main/resources/data.sql` carrega 5 empresas, 12 vagas e 6 pessoas.

Para olhar as tabelas, abra `http://localhost:8081/h2-console` e entre com:

| Campo | Valor |
| --- | --- |
| JDBC URL | `jdbc:h2:mem:vagas` |
| User Name | `sa` |
| Password | (vazio) |

## Organização

Todas as classes ficam no pacote `br.edu.faculdade.vagas`, separadas por responsabilidade:

| Camada | Classes | Papel |
| --- | --- | --- |
| Controller | `VagaController`, `EmpresaController`, `PessoaController`, `BuscaController`, `EstatisticasController` | recebe a requisição HTTP e devolve o status certo |
| Service | `VagaService`, `EmpresaService`, `PessoaService`, `BuscaService`, `EstatisticasService` | regras de negócio e conversão entre entidade e DTO |
| Repository | `VagaRepository`, `EmpresaRepository`, `PessoaRepository` | acesso ao banco com Spring Data JPA |
| Entidade | `Vaga`, `Empresa`, `Pessoa` | tabelas `vagas`, `empresas` e `pessoas` |
| DTO | `*Entrada` e `*Resposta` | o que o cliente envia e o que a API devolve |
| Erros | `TratadorDeErros` e as exceções | respostas de erro no formato Problem Details |

A entidade nunca sai pelo controller. O cliente manda um `*Entrada`, sem id, e recebe um `*Resposta`. Cada vaga pertence a uma empresa (`@ManyToOne`), e a vaga é criada informando o `empresaSlug`.

## Rotas

### Vagas

| Método | Rota | Sucesso | Erros |
| --- | --- | --- | --- |
| GET | `/vagas` | 200 | |
| GET | `/vagas/{id}` | 200 | 400, 404 |
| POST | `/vagas` | 201 + `Location` | 400, 422 |
| PUT | `/vagas/{id}` | 200 | 400, 404, 422 |
| DELETE | `/vagas/{id}` | 204 | 404 |
| GET | `/vagas/busca?area=&senioridade=&aceitaIniciante=` | 200 | 400 |
| GET | `/estatisticas` | 200 | |

Os três filtros da busca são opcionais e podem ser combinados. Busca sem resultado devolve 200 com lista vazia.

### Empresas

| Método | Rota | Sucesso | Erros |
| --- | --- | --- | --- |
| GET | `/empresas` | 200 | |
| GET | `/empresas/{slug}` | 200 | 404 |
| POST | `/empresas` | 201 + `Location` | 400, 409 |
| PUT | `/empresas/{slug}` | 200 | 400, 404, 409 |
| DELETE | `/empresas/{slug}` | 204 | 404, 409 |

Empresa é identificada pelo slug. Não dá para apagar uma empresa que ainda tem vagas.

### Pessoas

| Método | Rota | Sucesso | Erros |
| --- | --- | --- | --- |
| GET | `/pessoas` | 200 | |
| GET | `/pessoas/{id}` | 200 | 400, 404 |
| POST | `/pessoas` | 201 + `Location` | 400, 409 |
| PUT | `/pessoas/{id}` | 200 | 400, 404, 409 |
| DELETE | `/pessoas/{id}` | 204 | 404 |

O e-mail é único, sem diferenciar maiúsculas de minúsculas.

## Validação

| Recurso | Regras |
| --- | --- |
| Vaga | `titulo`, `area`, `senioridade` e `empresaSlug` obrigatórios; `titulo` até 120 caracteres; `descricao` até 1000; `aceitaIniciante` vira `false` se não vier |
| Empresa | `nome` e `slug` obrigatórios; `slug` só com letras minúsculas, números e hífen |
| Pessoa | todos os campos obrigatórios; `email` precisa ser um e-mail válido |

## Erros

Todo erro volta como `application/problem+json`:

| Status | Quando |
| --- | --- |
| 400 | campo inválido, JSON malformado ou id que não é número |
| 404 | recurso não encontrado |
| 409 | slug ou e-mail repetido, ou empresa com vagas sendo apagada |
| 422 | vaga apontando para uma empresa que não existe |

Exemplo de `POST /vagas` com corpo vazio:

```json
{
  "title": "Dados inválidos",
  "status": 400,
  "detail": "Um ou mais campos estão inválidos.",
  "instance": "/vagas",
  "campos": {
    "titulo": "o título é obrigatório",
    "area": "a área é obrigatória",
    "senioridade": "a senioridade é obrigatória",
    "empresaSlug": "o slug da empresa é obrigatório"
  }
}
```

## Coleção de requisições

Todas as chamadas estão em [`requisicoes.http`](requisicoes.http), incluindo os casos de erro. No IntelliJ é só abrir o arquivo e clicar no botão de executar ao lado de cada requisição. No VS Code precisa da extensão REST Client.

Alguns exemplos com `curl`:

```bash
curl http://localhost:8081/vagas

curl http://localhost:8081/vagas/busca?aceitaIniciante=true

curl -i -X POST http://localhost:8081/vagas \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Dev Java Pleno","area":"Back-end","senioridade":"Pleno","empresaSlug":"tucano-systems"}'

curl -i -X PUT http://localhost:8081/pessoas/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Ana Nogueira","email":"ana@exemplo.com","area":"Dados","senioridade":"Pleno"}'

curl -i -X DELETE http://localhost:8081/empresas/aurora-tech
```

Resposta de `GET /vagas/1`:

```json
{
  "id": 1,
  "titulo": "Pessoa Desenvolvedora Front-end Júnior",
  "descricao": "Telas do produto em React e Next.js, pareando com gente mais experiente.",
  "area": "Front-end",
  "senioridade": "Júnior",
  "local": "Remoto",
  "aceitaIniciante": true,
  "empresaSlug": "aurora-tech",
  "empresaNome": "Aurora Tech"
}
```

Resposta de `GET /estatisticas`:

```json
{
  "totalDeVagas": 12,
  "aceitamIniciante": 7,
  "porArea": { "Back-end": 3, "Dados": 3, "Front-end": 4, "Mobile": 2 },
  "porSenioridade": { "Estágio": 2, "Júnior": 6, "Pleno": 4 }
}
```

## Testes

```bash
./mvnw test
```
