package br.com.hexagonal.application.ports.in;

import br.com.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);

}
