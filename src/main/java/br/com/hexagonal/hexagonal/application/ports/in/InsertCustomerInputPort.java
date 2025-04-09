package br.com.hexagonal.hexagonal.application.ports.in;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);

}
