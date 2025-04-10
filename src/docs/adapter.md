#Explicação sobre a função do adapter na arquitetura hexagonal

- Adaptadores ficam localizados na parte masi externa da arquitetura , atuando de duas formas:
- recebem chamadas de métodos vindos de fora do sistema e encaminham para métodos adequados das portas de entrada.
- recebem chamadas vindas de dentro do sistema (classes de domínio) e direcionam para os sistema externo (exp banco de dados, outro sistema e etc).
