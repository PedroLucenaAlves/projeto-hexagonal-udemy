package br.com.hexagonal.hexagonal.application.ports.out;

import br.com.hexagonal.hexagonal.application.core.domain.Address;

/**
 * Portas são independentes de tecnologias
 *
 */

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);

}
