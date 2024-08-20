package Services.Funcionarios.Tipos;

import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioAdministrativo extends Funcionario {
    private int idFuncionarioAdministrativo;

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public static FuncionarioAdministrativo instanciaFuncionarioAdministrativo(ResultSet rs) throws SQLException {
        FuncionarioAdministrativo funcionarioAdministrativo = new FuncionarioAdministrativo();
        funcionarioAdministrativo.setIdFuncionarioAdministrativo(rs.getInt("idFuncionarioAdministrativo"));
        funcionarioAdministrativo.setNome(rs.getString("nome"));
        funcionarioAdministrativo.setEmail(rs.getString("email"));
        funcionarioAdministrativo.setCPF(rs.getString("CPF"));
        funcionarioAdministrativo.setTurno(rs.getString("turno"));
        funcionarioAdministrativo.setDataContratacao(rs.getDate("dataContratacao").toLocalDate());
        funcionarioAdministrativo.setSalario(rs.getDouble("salario"));
        funcionarioAdministrativo.setCargo(Cargo.valueOf(rs.getString("cargo")));
        return funcionarioAdministrativo;
    }

    public String toString() {
        return "Funcionário Administrativo {" + "\n" +
                "id Funcionario Administrativo: " + idFuncionarioAdministrativo + ".\n" +
                "Nome: " + getNome() + ".\n" +
                "Email: " + getEmail() + ".\n" +
                "CPF: " + getCPF() + ".\n" +
                "Turno: " + getTurno() + ".\n" +
                "Data da Contratação: " + getDataContratacao() + ".\n" +
                "Salário: " + getSalario() + ".\n" +
                "Cargo: " + getCargo() + ".\n" +
                '}';
    }
}
