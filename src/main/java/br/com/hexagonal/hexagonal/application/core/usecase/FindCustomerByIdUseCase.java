package br.com.hexagonal.hexagonal.application.core.usecase;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import br.com.hexagonal.hexagonal.application.ports.out.FindCustomerByIdOutputPort;

/**
 * Buscando cliente por id
 */

public class FindCustomerByIdUseCase implements FindCustomerByIdInputPort {

    private final FindCustomerByIdOutputPort findCustomerByIdOutputPort;

    public FindCustomerByIdUseCase(FindCustomerByIdOutputPort findCustomerByIdOutputPort){
        this.findCustomerByIdOutputPort = findCustomerByIdOutputPort;
    }

    //buscando cliente por id no banco e retornando para o controlador
    @Override
    public Customer find(String id){
        return findCustomerByIdOutputPort.find(id) //busca cliente por id
                .orElseThrow(() -> new RuntimeException("Customer Not Found !")); //caso nao exista o usuario estoura nossa exception
    }

}
