package Services.Funcionarios.Tipos;

import SGBD.InterfacesDAO.FuncionarioAdministrativoDAO;
import SGBD.JDBC.DaoFactory;
import Services.ENUM.Cargo;
import Services.Funcionarios.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioAdministrativo extends Funcionario {
    private int idFuncionarioAdministrativo;

    public FuncionarioAdministrativo(){
    }

    public FuncionarioAdministrativo(String nome, String email, String CPF, String turno, String dataContratacao, Double salario, Cargo cargo) {
        super(nome, email, CPF, turno, dataContratacao, salario, cargo);
    }

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public int getIdFuncionarioAdministrativo() {
        return idFuncionarioAdministrativo;
    }


    public static FuncionarioAdministrativo instanciaFuncionarioAdministrativo(ResultSet rs) throws SQLException {
        FuncionarioAdministrativo funcionarioAdministrativo = new FuncionarioAdministrativo();

        // Preenche os dados da superclasse Funcionario
        funcionarioAdministrativo.setNome(rs.getString("nome"));
        funcionarioAdministrativo.setEmail(rs.getString("email"));
        funcionarioAdministrativo.setCPF(rs.getString("CPF"));
        funcionarioAdministrativo.setTurno(rs.getString("turno"));
        funcionarioAdministrativo.setDataContratacao(rs.getString("dataContratacao"));
        funcionarioAdministrativo.setSalario(rs.getDouble("salario"));
        funcionarioAdministrativo.setCargo(Cargo.valueOf(rs.getString("cargo")));

        // Preenche o dado específico de FuncionarioAdministrativo
        funcionarioAdministrativo.setIdFuncionarioAdministrativo(rs.getInt("idFuncionarioAdministrativo"));

        return funcionarioAdministrativo;
    }

    public static void retornaListaFuncionariosAdministrativos(){
        FuncionarioAdministrativoDAO funcionarioAdministrativoDAO = DaoFactory.createFuncionarioAdminstrativoDAO();
        List<FuncionarioAdministrativo> funcionariosAdministrativos = funcionarioAdministrativoDAO.selectAll();

        for (FuncionarioAdministrativo funcionario : funcionariosAdministrativos) {
            System.out.println(funcionario);
        }
        System.out.println("FIM!");
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("ID Funcionário Administrativo: ").append(idFuncionarioAdministrativo); // Exibe o ID primeiro
        sb.append(super.toString()); // Inclui os atributos da classe mãe (Funcionario)
        return sb.toString();
    }

}
