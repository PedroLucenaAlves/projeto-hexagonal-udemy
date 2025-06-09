package br.com.hexagonal.adapters.out;

import br.com.hexagonal.adapters.out.repository.CustomerRepository;
import br.com.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import br.com.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.application.ports.out.FindCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component           //esse adapter implementa a porta de saida (chama o mundo externo)
public class FindCustomerByIdAdapter  implements FindCustomerByIdOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> find(String id) {
        var customerEntity = customerRepository.findById(id);
        //sempre ira retornar um optional de customer
        return customerEntity.map(entity -> customerEntityMapper.toCustomer(entity)); //map usado no optional para transformar objeto
    }
}
