package Services.Funcionarios;

import Services.ENUM.Cargo;
import java.time.LocalDate;
import java.util.List;

public class Funcionario {
    private String nome;
    private String email;
    private String CPF;
    private String turno;
    private LocalDate dataContratacao;
    private Double salario;
    private Cargo cargo;
    private List<Funcionario> listaDeFuncionarios;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Funcionario> getListaDeFuncionarios() {
        return listaDeFuncionarios;
    }


    public void enviarNotificacaoIncidente(SegurancaProfissional incidente) {
        incidente.enviarNotificacao(incidente);
    }

    public static void tabelaDeCargos(){
        Cargo.tabelaDeCargos();
    }

}
