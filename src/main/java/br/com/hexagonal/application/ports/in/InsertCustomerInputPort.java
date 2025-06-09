package br.com.hexagonal.application.ports.in;

import br.com.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);

}
