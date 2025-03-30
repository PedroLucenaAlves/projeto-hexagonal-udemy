package br.com.hexagonal.hexagonal.application.ports.out;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;

/**
 * Portas sao as interfaces e os adaptadores suas implementacoes
 */

public interface InsertCustomerOutputPort {

    void insert(Customer customer);

}
