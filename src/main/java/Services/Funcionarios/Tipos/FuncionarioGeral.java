package Services.Funcionarios.Tipos;

import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioGeral extends Funcionario {
    private int idFuncionarioGeral;

    public void setIdFuncionarioGeral(int idFuncionarioGeral) {
        this.idFuncionarioGeral = idFuncionarioGeral;
    }

    public static FuncionarioGeral instanciaFuncionarioGeral(ResultSet rs) throws SQLException {
        FuncionarioGeral funcionarioGeral = new FuncionarioGeral();
        funcionarioGeral.setIdFuncionarioGeral(rs.getInt("idFuncionarioGeral"));
        funcionarioGeral.setNome(rs.getString("nome"));
        funcionarioGeral.setEmail(rs.getString("email"));
        funcionarioGeral.setCPF(rs.getString("CPF"));
        funcionarioGeral.setTurno(rs.getString("turno"));
        funcionarioGeral.setDataContratacao(rs.getDate("dataContratacao").toLocalDate());
        funcionarioGeral.setSalario(rs.getDouble("salario"));
        funcionarioGeral.setCargo(Cargo.valueOf(rs.getString("cargo")));
        return funcionarioGeral;
    }

    @Override
    public String toString() {
        return "Funcionário Geral {" + "\n" +
                "id Funcionario Geral: " + idFuncionarioGeral + ".\n" +
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
