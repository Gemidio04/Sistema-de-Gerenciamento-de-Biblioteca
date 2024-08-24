package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.EstoqueDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Estoque.Estoque;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

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
                System.out.println("INSERT REALIZADO!");
            }catch (SQLException ex){
                throw new DBException(ex.getMessage());
            }finally {
                ConexaoBancoDeDados.closeStatement(preparedStatement);
            }
        }

    @Override
    public void update(Estoque estoque) {

    }

    @Override
    public void delete(Integer id){

    };

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
