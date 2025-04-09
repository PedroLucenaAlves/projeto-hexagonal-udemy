package br.com.hexagonal.hexagonal.adapters.out;

import br.com.hexagonal.hexagonal.adapters.out.repository.CustomerRepository;
import br.com.hexagonal.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.hexagonal.application.ports.out.InsertCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Adapter que implementa uma porta de saida que ira interagir com a base de dados
 */

@Component
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {

        /**
         * sem o mapstruct o mapeamento para o a entidade teria que ser manual:
         * CustomerEntity entity = new CustomerEntity();
         * entity.setNome(customer.getNome());
         * entity.setCpf(customer.getCpf());
         */

        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);
    }
}
