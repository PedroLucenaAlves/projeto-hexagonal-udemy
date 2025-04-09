package br.com.hexagonal.hexagonal.application.core.usecase;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.hexagonal.application.ports.out.FindCustomerByIdOutputPort;

/**
 * Buscando cliente por id
 */

public class FindCustomerByIdUseCase {

    private final FindCustomerByIdOutputPort findCustomerByIdOutputPort;

    public FindCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutputPort){
        this.findCustomerByIdOutputPort = findCustomerByIdOutputPort;
    }

    //buscando cliente por id no banco e retornando para o controlador
    public Customer find(String id){
        return findCustomerByIdOutputPort.find(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found !"));
    }

}
