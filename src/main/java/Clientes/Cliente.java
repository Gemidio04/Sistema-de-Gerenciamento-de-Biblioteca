package Clientes;

import java.sql.ResultSet;
import java.sql.SQLException;;

public class Cliente {
    private int idCliente;
    private String nome;
    private String email;
    private String CEP;
    private String endereco;
    private String dataCadastro;

    public Cliente() {
    }

    public Cliente(int idCliente, String nome, String email, String CEP, String endereco, String dataCadastro) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.CEP = CEP;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setCPF(String CEP) {
        this.CEP = CEP;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static Cliente instanciaCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCEP(rs.getString("CEP"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setDataCadastro(rs.getString("dataCadastro"));
        return cliente;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append("\n");
        sb.append("id do Cliente: ").append(idCliente).append(",\n");
        sb.append("Nome: ").append(nome).append(",\n");
        sb.append("Email: ").append(email).append(",\n");
        sb.append("CEP: ").append(CEP).append(",\n");
        sb.append("Endereço: ").append(endereco).append(",\n");
        sb.append("Data do Cadastro: ").append(dataCadastro).append(".\n");
        return sb.toString();
    }
}


