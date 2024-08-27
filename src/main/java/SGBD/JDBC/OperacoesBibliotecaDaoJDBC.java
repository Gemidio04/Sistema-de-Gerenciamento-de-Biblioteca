package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.OperacoesBibliotecaDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacoesBibliotecaDaoJDBC extends ConexaoDAO implements OperacoesBibliotecaDAO {

    public OperacoesBibliotecaDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(OperacoesBiblioteca operacoesBiblioteca) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO Operacoes_Biblioteca VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, operacoesBiblioteca.getIdOperacoesBiblioteca());
            preparedStatement.setInt(2, operacoesBiblioteca.getIdFuncionarioAdministrativo());
            preparedStatement.setInt(3, operacoesBiblioteca.getQuantidadeLivrosVendidos());
            preparedStatement.setInt(4, operacoesBiblioteca.getQuantidadeLivrosEmprestados());
            preparedStatement.setInt(5, operacoesBiblioteca.getIdEstoque());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(OperacoesBiblioteca operacoesBiblioteca) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("UPDATE Operacoes_Biblioteca " +
                          "SET idOperacoesBiblioteca = ?, idFuncionarioAdministrativo = ?, " +
                          "quantidadeLivrosVendidos = ?, quantidadeLivrosEmprestados = ?, idEstoque = ? " +
                          "WHERE idOperacoesBiblioteca = ?");

            preparedStatement.setInt(1, operacoesBiblioteca.getIdOperacoesBiblioteca());
            preparedStatement.setInt(2, operacoesBiblioteca.getIdFuncionarioAdministrativo());
            preparedStatement.setInt(3, operacoesBiblioteca.getQuantidadeLivrosVendidos());
            preparedStatement.setInt(4, operacoesBiblioteca.getQuantidadeLivrosEmprestados());
            preparedStatement.setInt(5, operacoesBiblioteca.getIdEstoque());
            preparedStatement.setInt(6, operacoesBiblioteca.getIdOperacoesBiblioteca());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE REALIZADO! ");
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public OperacoesBiblioteca selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT OB.idOperacoesBiblioteca," +
                          "FA.idFuncionarioAdministrativo," +
                          "OB.quantidadeLivrosVendidos," +
                          "OB.quantidadeLivrosEmprestados," +
                          "E.idEstoque " +
                          "FROM Operacoes_Biblioteca OB " +
                          "JOIN Funcionario_administrativo FA " +
                          "ON FA.idFuncionarioAdministrativo = OB.idFuncionarioAdministrativo " +
                          "JOIN Estoque E " +
                          "ON OB.idEstoque = E.idEstoque " +
                          "WHERE idOperacoesBiblioteca = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return OperacoesBiblioteca.instanciaOperacoesBiblioteca(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }

    @Override
    public List<OperacoesBiblioteca> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM OPERACOES_BIBLIOTECA");
            resultSet = preparedStatement.executeQuery();

            List<OperacoesBiblioteca> listaOperacoesBiblioteca = new ArrayList<>();

            while(resultSet.next()){
                OperacoesBiblioteca operacoesBiblioteca = OperacoesBiblioteca.instanciaOperacoesBiblioteca(resultSet);
                listaOperacoesBiblioteca.add(operacoesBiblioteca);
            }
            System.out.print("Operações Biblioteca ");
            return listaOperacoesBiblioteca;
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }

}