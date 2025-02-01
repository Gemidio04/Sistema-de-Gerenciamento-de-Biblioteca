package Services.Funcionarios.Tipos;

import SGBD.InterfacesDAO.FuncionarioGeralDAO;
import SGBD.JDBC.DaoFactory;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioGeral extends Funcionario {
    private int idFuncionarioGeral;

    public FuncionarioGeral(String nome, String email, String CPF, String turno, String dataContratacao, Double salario, Cargo cargo) {
        super(nome, email, CPF, turno, dataContratacao, salario, cargo);
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

        // Preenche os dados da superclasse Funcionario
        funcionarioGeral.setNome(rs.getString("nome"));
        funcionarioGeral.setEmail(rs.getString("email"));
        funcionarioGeral.setCPF(rs.getString("CPF"));
        funcionarioGeral.setTurno(rs.getString("turno"));
        funcionarioGeral.setDataContratacao(rs.getString("dataContratacao"));
        funcionarioGeral.setSalario(rs.getDouble("salario"));
        funcionarioGeral.setCargo(Cargo.valueOf(rs.getString("cargo")));

        // Preenche o dado específico de FuncionarioAdministrativo
        funcionarioGeral.setIdFuncionarioGeral(rs.getInt("idFuncionarioGeral"));

        return funcionarioGeral;
    }

    public static void retornaListaFuncionariosGerais(){
        FuncionarioGeralDAO funcionarioGeralDAO = DaoFactory.createFuncionarioGeralDAO();
        List<FuncionarioGeral> funcionarioGeral = funcionarioGeralDAO.selectAll();

        for (FuncionarioGeral funcionario : funcionarioGeral) {
            System.out.println(funcionario);
        }
        System.out.println("FIM!");
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ID Funcionário Geral: ").append(idFuncionarioGeral); // Exibe o ID primeiro
        sb.append(super.toString()); // Inclui os atributos da classe mãe (Funcionario)
        return sb.toString();
    }
}
