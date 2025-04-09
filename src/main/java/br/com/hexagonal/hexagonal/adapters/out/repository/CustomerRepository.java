package br.com.hexagonal.hexagonal.adapters.out.repository;

import br.com.hexagonal.hexagonal.adapters.out.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
