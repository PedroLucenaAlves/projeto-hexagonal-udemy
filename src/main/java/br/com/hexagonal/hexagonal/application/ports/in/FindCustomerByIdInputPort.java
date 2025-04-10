package br.com.hexagonal.hexagonal.application.ports.in;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);

}
