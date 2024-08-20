package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.OperacoesBibliotecaDAO;
import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Estoque.Estoque;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperacoesBibliotecaDaoJDBC extends ConexaoDAO implements OperacoesBibliotecaDAO {

    public OperacoesBibliotecaDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(OperacoesBiblioteca operacoesBiblioteca) {

    }

    @Override
    public void update(OperacoesBiblioteca operacoesBiblioteca) {

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
        return List.of();
    }

}