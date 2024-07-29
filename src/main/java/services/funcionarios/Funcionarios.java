package services.funcionarios;

import java.util.Date;

public class Funcionarios {
    private String nome;
    private String email;
    private String CPF;
    private String turno;
    private Date dataContratacao;
    private Cargo cargo;

    public Funcionarios(){
    }

    public Funcionarios(String nome, String email, String CPF, String turno, Date dataContratacao, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.turno = turno;
        this.dataContratacao = dataContratacao;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTurno() {
        return turno;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
