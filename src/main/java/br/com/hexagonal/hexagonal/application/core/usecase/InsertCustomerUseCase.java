package br.com.hexagonal.hexagonal.application.core.usecase;

import br.com.hexagonal.hexagonal.application.core.domain.Customer;
import br.com.hexagonal.hexagonal.application.ports.in.InsertCustomerInputPort;
import br.com.hexagonal.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import br.com.hexagonal.hexagonal.application.ports.out.InsertCustomerOutputPort;
import br.com.hexagonal.hexagonal.application.ports.out.SendCpfForValidationOutPutPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    private final InsertCustomerOutputPort insertCustomerOutputPort;

    private final SendCpfForValidationOutPutPort sendCpfForValidationOutPutPort;

    public InsertCustomerUseCase(
            FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
            InsertCustomerOutputPort insertCustomerOutputPort,
            SendCpfForValidationOutPutPort sendCpfForValidationOutPutPort
    ){
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
        this.sendCpfForValidationOutPutPort = sendCpfForValidationOutPutPort;
    }


    @Override
    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodeOutputPort.find(zipCode); //endereco do cliente
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer); //insere o client no banco
        sendCpfForValidationOutPutPort.send(customer.getCpf());

    }

}
