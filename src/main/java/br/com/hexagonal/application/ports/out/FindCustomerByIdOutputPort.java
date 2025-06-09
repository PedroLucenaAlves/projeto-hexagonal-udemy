package br.com.hexagonal.application.ports.out;

import br.com.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    //Optional indica que o retorno pode estar ausente
    //podemos ou nao ter um usuario com um id
    Optional<Customer> find(String id);

}
