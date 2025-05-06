package br.com.hexagonal.hexagonal.adapters.in.consumer;

import br.com.hexagonal.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import br.com.hexagonal.hexagonal.adapters.in.consumer.message.CustomerMessage;
import br.com.hexagonal.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Classe para receber o cpf validado
 */

@Component
public class ReciveValidateCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "tp-cpf-validated", groupId = "hexagonal")
    public void receive(CustomerMessage customerMessage){
        var customer = customerMessageMapper.toCustomer(customerMessage); //transformando objeto para o tipo customer
        //enviando dados recebidos para alterar os dados do cliente (update)
        updateCustomerInputPort.update(customer, customerMessage.getZipCode());
    }

}
