package br.com.hexagonal.hexagonal.adapters.in.consumer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe representa o objeto do tópico do Kafka para atualizar cliente
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMessage {

    private String id;
    private String name;
    private String zipCode;
    private String cpf;
    private Boolean isValidCpf;

}
