package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.FuncionarioAdministrativoDAO;
import SGBD.InterfacesDAO.FuncionarioDAO;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioAdministrativoDaoJDBC extends ConexaoDAO implements FuncionarioDAO, FuncionarioAdministrativoDAO {

    public FuncionarioAdministrativoDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(FuncionarioAdministrativo funcionarioAdministrativo) {
       PreparedStatement preparedStatement = null;

       try {
           preparedStatement = connection.prepareStatement
                   ("INSERT INTO Funcionario_administrativo VALUES (?,?,?,?,?,?,?,?)");

           preparedStatement.setInt(1, funcionarioAdministrativo.getIdFuncionarioAdministrativo());
           preparedStatement.setString(2, funcionarioAdministrativo.getNome());
           preparedStatement.setString(3, funcionarioAdministrativo.getEmail());
           preparedStatement.setString(4, funcionarioAdministrativo.getCPF());
           preparedStatement.setString(5, funcionarioAdministrativo.getTurno());
           preparedStatement.setString(6, funcionarioAdministrativo.getDataContratacao());
           preparedStatement.setDouble(7, funcionarioAdministrativo.getSalario());
           preparedStatement.setString(8, String.valueOf(funcionarioAdministrativo.getCargo()));
           preparedStatement.executeUpdate();
           System.out.println("INSERT REALIZADO!");
       }catch (SQLException ex){
           throw new DBException(ex.getMessage());
       }finally {
           ConexaoBancoDeDados.closeStatement(preparedStatement);
       }
    }

    @Override
    public void update(FuncionarioAdministrativo funcionarioAdministrativo) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Funcionario_Administrativo SET " +
                            "idFuncionarioAdministrativo = ?, nome = ?, email = ?, CPF = ?, " +
                            "turno = ?, dataContratacao = ?, salario = ?, cargo = ? " +
                            "WHERE idFuncionarioAdministrativo = ?")) {

                preparedStatement.setInt(1, funcionarioAdministrativo.getIdFuncionarioAdministrativo());
                preparedStatement.setString(2, funcionarioAdministrativo.getNome());
                preparedStatement.setString(3, funcionarioAdministrativo.getEmail());
                preparedStatement.setString(4, funcionarioAdministrativo.getCPF());
                preparedStatement.setString(5, funcionarioAdministrativo.getTurno());
                preparedStatement.setString(6, funcionarioAdministrativo.getDataContratacao());
                // Define salário ou NULL:
                preparedStatement.setObject(7, funcionarioAdministrativo.getSalario(), java.sql.Types.DOUBLE);
                // Define cargo ou NULL:
                preparedStatement.setObject(8, funcionarioAdministrativo.getCargo() != null ?
                        funcionarioAdministrativo.getCargo().name() : null, java.sql.Types.VARCHAR);
                preparedStatement.setInt(9, funcionarioAdministrativo.getIdFuncionarioAdministrativo());
                preparedStatement.executeUpdate();
                System.out.println("UPDATE REALIZADO!");
            } catch (SQLException ex) {
                throw new DBException(ex.getMessage());
            }
        }


    @Override
    public void delete(Integer idFuncionarioAdministrativo) {
            PreparedStatement preparedStatement = null;

            try{
                preparedStatement = connection.prepareStatement("DELETE FROM Funcionario_Administrativo WHERE idFuncionarioAdministrativo = ?");
                preparedStatement.setInt(1, idFuncionarioAdministrativo);
                int linhas = preparedStatement.executeUpdate();

                if (linhas == 0)
                    throw new DBException("O idFuncionarioAdministrativo fornecido não existe!");
                System.out.println("DELETE REALIZADO!");
            }catch(SQLException ex){
                throw new DBException(ex.getMessage());
            }finally {
                ConexaoBancoDeDados.closeStatement(preparedStatement);
            }
        }

    @Override
    public FuncionarioAdministrativo selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Funcionario_Administrativo WHERE idFuncionarioAdministrativo = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return (FuncionarioAdministrativo) FuncionarioAdministrativo.instanciaFuncionarioAdministrativo(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Funcionario> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT FA.idFuncionarioAdministrativo, FA.nome, FA.email, FA.CPF, FA.turno, " +
                    "DATE_FORMAT(FA.dataContratacao, '%d/%m/%Y') AS dataContratacao, " +
                    "FA.salario, FA.cargo FROM FUNCIONARIO_ADMINISTRATIVO FA; ");
            resultSet = preparedStatement.executeQuery();

            List<Funcionario> listaFuncionarioAdministrativo = new ArrayList<>();

            while (resultSet.next()){
                FuncionarioAdministrativo funcionarioAdministrativo =
                FuncionarioAdministrativo.instanciaFuncionarioAdministrativo(resultSet);
                Funcionario funcionario = Funcionario.instanciaFuncionario(resultSet);

                listaFuncionarioAdministrativo.add(funcionarioAdministrativo);
                listaFuncionarioAdministrativo.add(funcionario);
            }
            System.out.print("Funcionários Administrativos: ");
            return listaFuncionarioAdministrativo;
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }
    }

}
