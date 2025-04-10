package br.com.hexagonal.hexagonal.application.ports.in;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);

}
