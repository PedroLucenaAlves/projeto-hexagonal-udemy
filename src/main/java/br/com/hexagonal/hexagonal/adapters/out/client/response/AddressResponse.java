package br.com.hexagonal.hexagonal.adapters.out.client.response;

import lombok.Data;

/**
 * Classe de response do nosso client
 */

@Data //cria getters e setters, hash code, etc
public class AddressResponse {

    private String street;

    private String city;

    private String state;

}
