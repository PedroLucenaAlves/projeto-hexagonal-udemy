package br.com.hexagonal.hexagonal.adapters.in.controller.mapper;

import br.com.hexagonal.hexagonal.adapters.in.controller.request.CustomerRequest;
import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    //passa um CustomerRequest e converte para Customer

    //ignorando campos de customer que não existem em customerRequest:
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    Customer toCustomer(CustomerRequest customerRequest);

}
