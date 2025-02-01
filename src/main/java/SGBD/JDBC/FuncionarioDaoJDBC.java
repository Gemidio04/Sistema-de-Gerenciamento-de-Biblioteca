package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.FuncionarioDAO;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDaoJDBC extends ConexaoDAO implements FuncionarioDAO {

    public FuncionarioDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void delete(Integer idFuncionario) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Funcionario_Geral WHERE idFuncionarioGeral = ?");
            preparedStatement.setInt(1, idFuncionario);
            int linhas = preparedStatement.executeUpdate();

            if (linhas == 0)
                throw new DBException("O idFuncionario fornecido não existe!");
            //System.out.println("DELETE REALIZADO!");
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public Funcionario selectById(int id) {
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
}
