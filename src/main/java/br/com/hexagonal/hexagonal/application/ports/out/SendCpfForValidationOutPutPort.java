package br.com.hexagonal.hexagonal.application.ports.out;

public interface SendCpfForValidationOutPutPort {

    void send(String cpf);

}
