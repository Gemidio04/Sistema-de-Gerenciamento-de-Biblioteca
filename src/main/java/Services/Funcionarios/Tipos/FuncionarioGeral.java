package Services.Funcionarios.Tipos;

import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioGeral extends Funcionario {
    private int idFuncionarioGeral;

    public FuncionarioGeral(int idFuncionarioGeral, String nome, String email, String CPF, String turno, String dataContratacao, Double salario, Cargo cargo) {
        super(nome, email, CPF, turno, dataContratacao, salario, cargo);
        this.idFuncionarioGeral = idFuncionarioGeral;
    }

    public FuncionarioGeral() {
    }

    public int getIdFuncionarioGeral() {
        return idFuncionarioGeral;
    }

    public void setIdFuncionarioGeral(int idFuncionarioGeral) {
        this.idFuncionarioGeral = idFuncionarioGeral;
    }

    public static FuncionarioGeral instanciaFuncionarioGeral(ResultSet rs) throws SQLException {
        FuncionarioGeral funcionarioGeral = new FuncionarioGeral();
        funcionarioGeral.setIdFuncionarioGeral(rs.getInt("idFuncionarioGeral"));
        return funcionarioGeral;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("id Funcionario Geral: ").append(idFuncionarioGeral);
        return sb.toString();
    }
}
