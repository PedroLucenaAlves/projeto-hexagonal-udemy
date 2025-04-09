package br.com.hexagonal.hexagonal.adapters.out.repository.mapper;

import br.com.hexagonal.hexagonal.adapters.out.repository.entity.CustomerEntity;
import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //permite que o mapStruct registre essa interface como um bean do Spring
public interface CustomerEntityMapper {

    //converte customer em customerEntity
    CustomerEntity toCustomerEntity(Customer customer);

}
