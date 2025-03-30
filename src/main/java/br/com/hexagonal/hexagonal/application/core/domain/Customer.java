package br.com.hexagonal.hexagonal.application.core.domain;

/**
 * Tudo que estiver dentro do package core deve estar isolado de qualquer outra tecnologia como frameworks, nao pode ser acessado direto
 */

public class Customer {

    private String id;

    private String name;

    private Address address;

    private String cpf;

    private Boolean isValidCPF;

    //quando criarmos um cliente o cpf so sera validado apos a nossa api de validacao do cpf, retornando o cpf como valido
    public Customer(){
        this.isValidCPF = false;
    }

    public Customer(Boolean isValidCPF, String cpf, Address address, String name, String id) {
        this.isValidCPF = isValidCPF;
        this.cpf = cpf;
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getValidCPF() {
        return isValidCPF;
    }

    public void setValidCPF(Boolean validCPF) {
        isValidCPF = validCPF;
    }
}
