package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.EstoqueDAO;
import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Estoque.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstoqueDaoJDBC extends ConexaoDAO implements EstoqueDAO {

    public EstoqueDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Estoque funcionario) {

    }

    @Override
    public void update(Estoque funcionario) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Estoque selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Estoque WHERE idEstoque = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Estoque.instanciaEstoque(resultSet);
            }
            return null;
        } catch(SQLException ex) {
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Estoque> selectAll() {
        return List.of();
    }
}
