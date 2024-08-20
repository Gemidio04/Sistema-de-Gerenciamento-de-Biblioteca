package SGBD.JDBC;

import Clientes.Cliente;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.ClienteDAO;
import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDaoJDBC extends ConexaoDAO implements ClienteDAO {

    public ClienteDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Cliente cliente) {
    }

    @Override
    public void update(Cliente cliente) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Cliente selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Cliente WHERE idCliente = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Cliente.instanciaCliente(resultSet);
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
    public List<Cliente> selectAll() {
        return List.of();
    }
}
