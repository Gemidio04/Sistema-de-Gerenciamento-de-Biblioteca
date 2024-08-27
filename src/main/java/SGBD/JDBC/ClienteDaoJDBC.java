package SGBD.JDBC;

import Clientes.Cliente;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.ClienteDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClienteDaoJDBC extends ConexaoDAO implements ClienteDAO {

    public ClienteDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Cliente cliente) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement
            ("INSERT INTO Cliente VALUES (?,?,?,?,?,?) ");
            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getCEP());
            preparedStatement.setString(5, cliente.getEndereco());
            preparedStatement.setString(6, cliente.getDataCadastro());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Cliente cliente) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("UPDATE Cliente SET " +
                          "idCliente = ?, nome = ?, email = ?, " +
                          "CEP = ?, endereco = ?, dataCadastro = ? " +
                          "WHERE idCliente = ?");

            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getCEP());
            preparedStatement.setString(5, cliente.getEndereco());
            preparedStatement.setString(6, cliente.getDataCadastro());
            preparedStatement.setInt(7, cliente.getIdCliente());
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement
                    ("SELECT CE.idCliente, CE.nome, CE.email, CE.CEP, CE.endereco, " +
                    "DATE_FORMAT(CE.dataCadastro, '%d/%m/%Y') AS dataCadastro FROM Cliente CE");
            resultSet = preparedStatement.executeQuery();

            List<Cliente> clientes = new ArrayList<>();

            while (resultSet.next()) {
                Cliente cliente = Cliente.instanciaCliente(resultSet);
                clientes.add(cliente);
            }

            // Verifica se a lista de clientes está vazia. Se sim, imprime "null.".
            // Caso contrário, imprime "Clientes:" seguido por cada cliente formatado.
            System.out.println(clientes.isEmpty() ? "null." : "Clientes:\n" +
            // Junta cada cliente com ",\n\n" entre eles, o que adiciona uma vírgula e duas quebras de linha
            String.join("\n,", clientes.stream()
            // Remove a quebra de linha final após o ponto em cada cliente
            .map(cliente -> cliente.toString().replaceAll("\\.\n$", "."))
            // Converte o stream em um array de Strings para ser utilizado pelo String.join
            .toArray(String[]::new)));
            return clientes;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }
}
