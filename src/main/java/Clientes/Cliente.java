package Clientes;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String email;
    private String CEP;
    private String endereco;
    private LocalDate dataCadastro;

    public Cliente(){
    }

    public Cliente(String nome, String email, String CEP, String endereco, LocalDate dataCadastro) {
        this.nome = nome;
        this.email = email;
        this.CEP = CEP;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCEP() {
        return CEP;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ",\n" +
                "Email: " + email + ",\n" +
                "CEP: " + CEP + ",\n" +
                "Endereco: " + endereco + ",\n" +
                "DataCadastro: " + dataCadastro + "\n";
    }

}
