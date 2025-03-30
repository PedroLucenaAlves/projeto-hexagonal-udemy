package br.com.hexagonal.hexagonal.adapters.out.client.mapper;

import br.com.hexagonal.hexagonal.adapters.out.client.response.AddressResponse;
import br.com.hexagonal.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

/**
 * MapStruct gera codigo automatico que realiza a conversao de objetos
 */

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    //convertendo o addressResponse para um address
    Address toAddress(AddressResponse adressResponse);

}
