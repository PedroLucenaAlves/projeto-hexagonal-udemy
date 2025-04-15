# Arquitetura Hexagonal (Ports and Adapters)

## Conceitos Fundamentais

### 1. Domínio (ou Core / Núcleo da Aplicação)
- Onde reside a **regra de negócio** da aplicação.
- Contém as **entidades** (ex: `Customer`, `Order`) e os **casos de uso** (ex: `FindCustomerByIdUseCase`).
- É completamente **independente de frameworks, bancos ou tecnologias externas**.

---

### 2. Portas (Ports)
As portas definem **interfaces de comunicação com o núcleo** da aplicação.

#### 🔹 Portas de Entrada (Input Ports)
- Interfaces que o **mundo externo** (como Controllers, Mensageria, CLI) utiliza para **interagir com os casos de uso**.
- Exemplo: `FindCustomerByIdInputPort`.

#### 🔸 Portas de Saída (Output Ports)
- Interfaces que o **núcleo da aplicação usa para interagir com o mundo externo**.
- Exemplo: `FindCustomerByIdOutputPort` (implementada por um adapter que acessa o banco de dados).

---

### 3. Casos de Uso (Use Cases)
- São classes que **implementam as Portas de Entrada**.
- Contêm a lógica de orquestração da regra de negócio.
- **Nunca acessam diretamente frameworks, banco de dados, APIs externas, etc**.
- Utilizam as **Portas de Saída** para buscar ou persistir dados.

---

### 4. Adapters (Adaptadores)

#### Entrada (Input Adapters)
- Responsáveis por **traduzir requisições externas** (HTTP, Kafka, CLI, etc.) para chamadas aos casos de uso.
- Exemplos: Controllers REST, Consumers Kafka.

#### Saída (Output Adapters)
- Responsáveis por **implementar as Portas de Saída**, permitindo o núcleo acessar infraestrutura externa (DB, APIs, etc.).
- Exemplo: Repositórios JPA, FeignClient, MongoDB Repositories.

---

### 5. Controllers
- São Adapters de Entrada.
- Responsáveis por **receber requisições** (geralmente HTTP), extrair parâmetros, e **chamar os casos de uso via Input Ports**.
- Também convertem os objetos de domínio para DTOs de saída.

---

## Fluxo Típico da Arquitetura Hexagonal

```text
1. HTTP request chega no Controller (Adapter de Entrada)
2. Controller chama o Use Case via Input Port
3. O Use Case executa a lógica de negócio
4. Se necessário, chama uma Output Port (ex: para buscar dados)
5. Um Adapter implementa essa Output Port e acessa o recurso externo (DB, API, etc.)
6. O resultado retorna para o Use Case
7. O Use Case retorna para o Controller
8. O Controller devolve a resposta HTTP
