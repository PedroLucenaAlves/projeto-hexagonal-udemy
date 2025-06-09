package br.com.hexagonal.application.ports.out;

import br.com.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);

}
