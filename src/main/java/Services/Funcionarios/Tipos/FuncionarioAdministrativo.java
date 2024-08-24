package Services.Funcionarios.Tipos;

import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioAdministrativo extends Funcionario {
    private int idFuncionarioAdministrativo;

    public FuncionarioAdministrativo(){
    }

    public FuncionarioAdministrativo(int idFuncionarioAdministrativo, String nome, String email, String CPF, String turno, String dataContratacao, Double salario, Cargo cargo) {
        super(nome, email, CPF, turno, dataContratacao, salario, cargo);
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public int getIdFuncionarioAdministrativo() {
        return idFuncionarioAdministrativo;
    }

    public static FuncionarioAdministrativo instanciaFuncionarioAdministrativo(ResultSet rs) throws SQLException {
        FuncionarioAdministrativo funcionarioAdministrativo = new FuncionarioAdministrativo();
        funcionarioAdministrativo.setIdFuncionarioAdministrativo(rs.getInt("idFuncionarioAdministrativo"));
        return funcionarioAdministrativo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n");
        sb.append("id Funcionario Administrativo: ").append(idFuncionarioAdministrativo);
        return sb.toString();
    }
}
