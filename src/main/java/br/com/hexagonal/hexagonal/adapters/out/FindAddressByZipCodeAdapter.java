package br.com.hexagonal.hexagonal.adapters.out;

import br.com.hexagonal.hexagonal.adapters.out.client.FindAddressByZipCodeClient;
import br.com.hexagonal.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import br.com.hexagonal.hexagonal.application.core.domain.Address;
import br.com.hexagonal.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Adapter e uma implementacao da porta de saida ou entrada
 */

@Component //spring gerencia a classe
public class FindAddressByZipCodeAdapter implements FindAddressByZipCodeOutputPort {

    @Autowired
    private FindAddressByZipCodeClient findAddressByZipCodeClient;

    @Autowired
    private AddressResponseMapper addressResponseMapper; //mapper converte objetos

    @Override
    public Address find(String zipCode) {

        var addressResponse = findAddressByZipCodeClient.find(zipCode); //usando feign client para buscar um endereço via api externa

        return addressResponseMapper.toAddress(addressResponse); //convertendo resposta para modelo interno
    }


}
