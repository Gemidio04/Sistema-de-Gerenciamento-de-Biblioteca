package Clientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Cliente {
    private int idCliente;
    private String nome;
    private String email;
    private String CEP;
    private String endereco;
    private LocalDate dataCadastro;

    public Cliente(){
    }

    public Cliente(String nome, String email, String CEP, String endereco, LocalDate dataCadastro) {
        this.idCliente = 1;
        this.nome = nome;
        this.email = email;
        this.CEP = CEP;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public void setId(int idCliente) {
        this.idCliente = idCliente;
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

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setCPF(String CEP) {
        this.CEP = CEP;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static Cliente instanciaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("idCliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCPF(rs.getString("cep"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());
        return cliente;
    }

//    @Override
//    public String toString() {
//        return  "Cliente {" + "\n" +
//                "id do Cliente: " + idCliente + ",\n" +
//                "Nome: " + nome + ",\n" +
//                "Email: " + email + ",\n" +
//                "CEP: " + CEP + ",\n" +
//                "Endereco: " + endereco + ",\n" +
//                "DataCadastro: " + dataCadastro + "\n";
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente {").append("\n")
                .append("id do Cliente: ").append(idCliente).append(",\n")
                .append("Nome: ").append(nome).append(",\n")
                .append("Email: ").append(email).append(",\n")
                .append("CEP: ").append(CEP).append(",\n")
                .append("Endereco: ").append(endereco).append(",\n")
                .append("DataCadastro: ").append(dataCadastro).append("\n");
        return sb.toString();
    }


}
