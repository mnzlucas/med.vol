# med.vol API

API REST da aplicação Voll.med para gerenciamento de médicos, pacientes e agendamento de consultas.

## Pré-requisitos

- JDK 17 instalado
- Maven Wrapper incluído no projeto (`./mvnw`)

## Como rodar localmente

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

A aplicação Spring Boot será iniciada usando as configurações locais do projeto.

## Regras de agendamento

Ao criar uma consulta, os validadores em `src/main/java/med/voll/api/validator/` aplicam as seguintes regras:

- Horário da clínica: a consulta deve ser marcada de segunda a sexta-feira, das 7h às 18h. Agendamentos aos sábados, domingos ou fora desse intervalo são rejeitados.
- Antecedência mínima: a consulta deve ser marcada com pelo menos 30 minutos de antecedência em relação ao momento da solicitação.
- Paciente ativo: o paciente informado deve estar ativo no sistema.
- Médico ativo: quando um médico é informado na solicitação, ele deve estar ativo no sistema.
- Médico livre: quando médico e data/hora são informados, o validador consulta se existe agendamento para esse médico no mesmo horário; no código atual, a solicitação é rejeitada quando essa consulta retorna `false`, com a mensagem de que o médico já possui outro agendamento nesse horário.
