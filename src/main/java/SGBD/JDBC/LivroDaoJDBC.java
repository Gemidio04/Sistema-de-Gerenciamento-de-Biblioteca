package SGBD.JDBC;

import Livros.Livro;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.LivroDAO;

import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDaoJDBC extends ConexaoDAO implements LivroDAO {

    public LivroDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Livro livro) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("INSERT INTO Livro VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, livro.getIsbn());
            preparedStatement.setString(2, livro.getTitulo());
            preparedStatement.setString(3, livro.getEditora());
            preparedStatement.setString(4, livro.getGenero());
            preparedStatement.setString(5, livro.getAutor());
            preparedStatement.setString(6, livro.getDataPublicacao());
            preparedStatement.setString(7, livro.getSinopse());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");
        }catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Livro livro) {
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement
                    ("UPDATE Livro SET " +
                    "isbn = ?, titulo = ?, editora = ?, genero = ?, " +
                    "autor = ?, dataPublicacao = ?, sinopse = ? " +
                    "WHERE isbn = ?");

            preparedStatement.setString(1, livro.getIsbn());
            preparedStatement.setString(2, livro.getTitulo());
            preparedStatement.setString(3, livro.getEditora());
            preparedStatement.setString(4, livro.getGenero());
            preparedStatement.setString(5, livro.getAutor());
            preparedStatement.setString(6, livro.getDataPublicacao());
            preparedStatement.setString(7, livro.getSinopse());
            preparedStatement.setString(8, livro.getIsbn());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE REALIZADO!");

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
    public Livro selectById(String id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
            ("SELECT * FROM Livro WHERE isbn = ? ");

            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Livro.instanciaLivro(resultSet);
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
    public List<Livro> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM Livro");
            resultSet = preparedStatement.executeQuery();

            List<Livro> livros = new ArrayList<>();

            while(resultSet.next()){
                Livro livro = Livro.instanciaLivro(resultSet);
                livros.add(livro);
            }
            System.out.print("Livros ");
            return livros;
        }catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }
}
