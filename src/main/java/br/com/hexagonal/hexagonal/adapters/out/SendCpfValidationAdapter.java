package br.com.hexagonal.hexagonal.adapters.out;

import br.com.hexagonal.hexagonal.application.ports.out.SendCpfForValidationOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Adaptador de envio do cpf ao Kafka
 */

@Component
public class SendCpfValidationAdapter implements SendCpfForValidationOutPutPort {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //Envio do Kafka
    @Override
    public void send(String cpf) {
        kafkaTemplate.send("tp-cpf-validation" , cpf);
    }
}
