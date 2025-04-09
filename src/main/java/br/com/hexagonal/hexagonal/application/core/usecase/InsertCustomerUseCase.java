package br.com.hexagonal.hexagonal.application.core.usecase;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.hexagonal.application.ports.in.InsertCustomerInputPort;
import br.com.hexagonal.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import br.com.hexagonal.hexagonal.application.ports.out.InsertCustomerOutputPort;

/**
 * Classe ira inserir um cliente
 * No core por ser isolado das outras tecnologias, nos comunicamos atraves de portas
 */

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    private final InsertCustomerOutputPort insertCustomerOutputPort;

    //injetando a classe sem autowired
    public InsertCustomerUseCase(
            FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
            InsertCustomerOutputPort insertCustomerOutputPort
    ){
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
    }


    @Override
    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodeOutputPort.find(zipCode); //endereco do cliente
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer); //insere o client no banco

    }

}
