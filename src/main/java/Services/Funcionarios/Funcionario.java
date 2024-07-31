package Services.Funcionarios;

import java.time.LocalDate;

public class Funcionario {
    private String nome;
    private String email;
    private String CPF;
    private String turno;
    private LocalDate dataContratacao;
    private Double salario;
    private Cargo cargo;

    public Funcionario(){
    }
    public Funcionario(String nome, String email, String CPF, String turno, LocalDate dataContratacao, Double salario, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.turno = turno;
        this.dataContratacao = dataContratacao;
        this.salario = salario;
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

    public LocalDate getDataContratacao() {
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

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void promover(Cargo novoCargo){
        this.cargo = novoCargo;
    }
}
