package br.com.hexagonal.hexagonal.application.ports.out;

import br.com.hexagonal.hexagonal.application.core.domain.Address;

/**
 * Portas sao as interfaces e os adaptadores suas implementacoes
 */

public interface FindAddressByZipCodeOutputPort {

    //buscando endereço por cep
    Address find(String zipCode);

}
