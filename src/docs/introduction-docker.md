# Conceitos Docker e Docker Compose

Este documento serve como um guia de estudo e referência rápida sobre os conceitos do Docker e  o funcionamento do Docker Compose baseado no meu projeto.

---

## 1. Conceitos Fundamentais do Docker 🐳

### Como o Docker Funciona? (O "Comando Mágico")

O Docker funciona como um "comando mágico" que automatiza e padroniza a configuração de ambientes de software. Ele permite que você "empacote" uma aplicação com todas as suas dependências (bibliotecas, arquivos de configuração, etc.) em uma unidade isolada chamada **contêiner**.

- **Docker (`docker run`)**: É o comando para uma **única ferramenta**. Com `docker run postgres`, você tem um banco de dados PostgreSQL rodando em segundos, sem instalá-lo na sua máquina.
- **Docker Compose (`docker-compose up`)**: É o "super comando" para **múltiplas ferramentas ao mesmo tempo**. Ele lê um arquivo de configuração (`.yml`) e orquestra um ambiente completo, com vários contêineres que já se comunicam entre si.

**Principais Benefícios:**
- **Isolamento**: Cada contêiner é uma "caixinha" isolada. Você pode ter versões diferentes do mesmo software rodando sem conflitos.
- **Ambiente Limpo**: Nada é instalado diretamente no seu sistema operacional. Ao remover o contêiner, tudo desaparece sem deixar rastros.
- **Portabilidade**: O mesmo ambiente funciona exatamente da mesma forma no Windows, macOS ou Linux. Adeus ao "mas na minha máquina funciona"!
- **Automação**: Com um comando, todo o ambiente de desenvolvimento sobe ou desce, economizando um tempo enorme de configuração.

### O que é uma Imagem Docker? (A Receita do Bolo)

A **Imagem Docker** é a **"PLANTA BAIXA"** de uma casa ou a **"RECEITA DE BOLO"**.

- É um **pacote estático e imutável** que contém todas as instruções e arquivos necessários para criar um ambiente.
- A imagem em si não faz nada; ela é apenas um **molde/template**.

O **Contêiner Docker** é a **"CASA CONSTRUÍDA"** ou o **"BOLO PRONTO"**.

- É uma **instância em execução de uma imagem**. É o resultado vivo e real da sua "receita".
- Você pode criar vários contêineres idênticos a partir da mesma imagem.

Numa analogia de programação:
- **Imagem** está para uma **Classe** (a definição).
- **Contêiner** está para um **Objeto** (a instância em execução).

Essas imagens são geralmente baixadas do **Docker Hub**, um repositório público gigante com milhares de "receitas" prontas.

### O que significa `usuario/imagem:tag`?

A linha `image: 'confluentinc/cp-zookeeper:latest'` especifica exatamente qual imagem usar.

| Parte da String | Nome Técnico | Analogia | O que significa no seu Exemplo |
| :--- | :--- | :--- | :--- |
| `confluentinc` | Usuário / Organização | O "autor" da receita | A imagem é da empresa Confluent. |
| `cp-zookeeper` | Nome da Imagem | O "nome" da receita | É a imagem para o software ZooKeeper da Confluent. |
| `:latest` | Tag (Versão) | A "edição" da receita | Usar a versão mais recente disponível. |

**Atenção:** Para ambientes de **produção**, evite usar a tag `:latest`, pois ela pode mudar. É mais seguro fixar uma versão específica (ex: `:7.6.1`) para garantir que seu ambiente seja sempre reprodutível.

---

## 2. Analisando seu Arquivo `docker-compose.yml` ⚙️

Este arquivo define um ambiente multi-contêiner com duas stacks principais: uma para **Apache Kafka** e outra para **MongoDB**.

```yaml
# docker-compose.yml
services:
  zookeeper:
    image: 'confluentinc/cp-zookeeper:latest'
    networks:
      - broker-kafka
    ports:
      - '2181:2181'
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
  kafka:
    image: 'confluentinc/cp-kafka:latest'
    networks:
      - broker-kafka
    depends_on:
      - zookeeper
    ports:
      - '9092:9092'
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
  kafdrop:
    image: obsidiandynamics/kafdrop:latest
    networks:
      - broker-kafka
    depends_on:
      - kafka
    ports:
      - 9000:9000
    environment:
      KAFKA_BROKERCONNECT: kafka:29092
  mongo:
    image: mongo
    restart: always
    ports:
      - 27017:27017
    environment:
      MONGO_INITDB_ROOT_USERNAME: root
      MONGO_INITDB_ROOT_PASSWORD: example
  mongo-express:
    image: mongo-express
    restart: always
    ports:
      - 8083:8081
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: root
      ME_CONFIG_MONGODB_ADMINPASSWORD: example
      ME_CONFIG_MONGODB_URL: mongodb://root:example@mongo:27017/
networks:
  broker-kafka:
    driver: bridge
```

### Análise dos Serviços

- **`zookeeper`**: Coordenador essencial para o Kafka.
- **`kafka`**: O servidor de mensageria Kafka.
    - **`depends_on: - zookeeper`**: Garante que o Zookeeper inicie *antes* do Kafka.
    - **`KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181`**: O Kafka encontra o Zookeeper usando o nome do serviço (`zookeeper`) como endereço de rede, pois ambos estão na mesma rede Docker.
    - **`KAFKA_ADVERTISED_LISTENERS`**: Diz a outros como se conectar ao Kafka. `PLAINTEXT://kafka:29092` é para comunicação **interna** (entre contêineres). `PLAINTEXT_HOST://localhost:9092` é para comunicação **externa** (da sua máquina para o contêiner).
- **`kafdrop`**: Interface web para monitorar o Kafka. Acessível em `http://localhost:9000`.
- **`mongo`**: Banco de dados MongoDB.
- **`mongo-express`**: Interface web para gerenciar o MongoDB. Acessível em `http://localhost:8083`.

### Como Usar o Arquivo

1.  Salve o conteúdo acima em um arquivo chamado `docker-compose.yml`.
2.  No terminal, na mesma pasta do arquivo, execute:
    - **Para iniciar:** `docker-compose up` (ou `docker-compose up -d` para rodar em segundo plano).
    - **Para parar e remover tudo:** `docker-compose down`.
    - **Para ver os contêineres em execução:** `docker-compose ps`.