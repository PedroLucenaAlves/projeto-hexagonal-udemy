package br.com.hexagonal.application.ports.in;

import br.com.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);

}
