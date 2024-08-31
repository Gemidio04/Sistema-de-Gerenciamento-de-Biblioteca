package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.EstoqueDAO;
import Services.Estoque.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDaoJDBC extends ConexaoDAO implements EstoqueDAO {

    public EstoqueDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Estoque estoque) {
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = connection.prepareStatement
                        ("INSERT INTO Estoque VALUES (?,?,?)");

                preparedStatement.setInt(1, estoque.getIdEstoque());
                preparedStatement.setString(2, estoque.getIsbn());
                preparedStatement.setInt(3, estoque.getQuantidade());
                preparedStatement.executeUpdate();
                //System.out.println("INSERT REALIZADO!");
            }catch (SQLException ex){
                throw new DBException(ex.getMessage());
            }finally {
                ConexaoBancoDeDados.closeStatement(preparedStatement);
            }
        }

    @Override
    public void update(Estoque estoque) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Estoque SET " +
                        "idEstoque = ?, isbn = ?, quantidade = ? " +
                        "WHERE idEstoque = ?")) {

            preparedStatement.setInt(1, estoque.getIdEstoque());
            preparedStatement.setString(2, estoque.getIsbn());
            preparedStatement.setInt(3, estoque.getQuantidade());
            preparedStatement.setInt(4, estoque.getIdEstoque());
            preparedStatement.executeUpdate();
            //System.out.println("UPDATE REALIZADO!");
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }


    @Override
    public void delete(Integer idEstoque){
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement("DELETE FROM Estoque WHERE idEstoque = ?");
            preparedStatement.setInt(1, idEstoque);
            int linhas = preparedStatement.executeUpdate();

            if (linhas == 0)
                throw new DBException("O idEstoque fornecido não existe!");
            //System.out.println("DELETE REALIZADO!");
        }catch(SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
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
    public Estoque selectByIsbn(String isbn) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Estoque estoque = null;

        try {
            String sql = "SELECT * FROM Estoque WHERE isbn = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String isbnEstoque = resultSet.getString("isbn");
                int quantidade = resultSet.getInt("quantidade");
                estoque = new Estoque(isbnEstoque, quantidade);
            }
            return estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
           ConexaoBancoDeDados.closeResultSet(resultSet);
           ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Estoque> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM Estoque");
            resultSet = preparedStatement.executeQuery();

            List<Estoque> listaEstoque = new ArrayList<>();

            while (resultSet.next()){
                Estoque estoque = Estoque.instanciaEstoque(resultSet);
                listaEstoque.add(estoque);
            }
            System.out.print("Estoque: ");
            return listaEstoque;
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }
}
