package br.com.hexagonal.hexagonal.application.ports.out;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {

    void insert(Customer customer);

}
