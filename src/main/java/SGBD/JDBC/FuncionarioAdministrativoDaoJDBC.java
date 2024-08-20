package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.ConexaoDAO;
import SGBD.DAO.FuncionarioAdministrativoDAO;
import SGBD.DAO.FuncionarioDAO;
import SGBD.Exception.DBException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioAdministrativoDaoJDBC extends ConexaoDAO implements FuncionarioDAO, FuncionarioAdministrativoDAO {

    public FuncionarioAdministrativoDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Funcionario funcionario) {

    }

    @Override
    public void update(Funcionario funcionario) {

    }

    @Override
    public void delete(Integer id) {

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
        return List.of();
    }
}
