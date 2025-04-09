package br.com.hexagonal.hexagonal.application.ports.out;

import br.com.hexagonal.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);

}
