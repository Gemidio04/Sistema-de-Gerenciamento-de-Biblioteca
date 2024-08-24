package Services.Funcionarios;

import Services.ENUM.Cargo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Funcionario {
    private String nome;
    private String email;
    private String CPF;
    private String turno;
    private String dataContratacao;
    private Double salario;
    private Cargo cargo;
    private List<Funcionario> listaDeFuncionarios;

    public Funcionario(){
    }

    public Funcionario(String nome, String email, String CPF, String turno, String dataContratacao, Double salario, Cargo cargo) {
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

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
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

    public static Funcionario instanciaFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(rs.getString("nome"));
        funcionario.setEmail(rs.getString("email"));
        funcionario.setCPF(rs.getString("CPF"));
        funcionario.setTurno(rs.getString("turno"));
        funcionario.setDataContratacao(rs.getString("dataContratacao"));
        funcionario.setSalario(rs.getDouble("salario"));
        funcionario.setCargo(Cargo.valueOf(rs.getString("cargo")));
        return funcionario;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("Nome: ").append(nome).append(",\n");
        sb.append("Email: ").append(email).append(",\n");
        sb.append("CPF: ").append(CPF).append(",\n");
        sb.append("Turno: ").append(turno).append(",\n");
        sb.append("Data da Contratacao: ").append(dataContratacao).append(",\n");
        sb.append("Salário: ").append(salario).append(",\n");
        sb.append("Cargo: ").append(cargo).append(".\n");
        return sb.toString();
    }
}
