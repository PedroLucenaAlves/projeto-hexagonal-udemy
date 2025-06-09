package br.com.hexagonal.adapters.out;

import br.com.hexagonal.adapters.out.client.FindAddressByZipCodeClient;
import br.com.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import br.com.hexagonal.application.core.domain.Address;
import br.com.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindAddressByZipCodeAdapter implements FindAddressByZipCodeOutputPort {

    @Autowired
    private FindAddressByZipCodeClient findAddressByZipCodeClient;

    @Autowired
    private AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(String zipCode) {

        var addressResponse = findAddressByZipCodeClient.find(zipCode);

        return addressResponseMapper.toAddress(addressResponse);
    }


}
