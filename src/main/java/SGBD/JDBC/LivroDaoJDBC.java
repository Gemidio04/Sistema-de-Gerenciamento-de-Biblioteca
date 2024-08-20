package SGBD.JDBC;

import Livros.Livro;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.LivroDAO;

import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LivroDaoJDBC extends ConexaoDAO implements LivroDAO {

    public LivroDaoJDBC(Connection connection) {
        super(connection);
    }


    @Override
    public void insert(Livro livro) {

    }

    @Override
    public void update(Livro livro) {

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
        return List.of();
    }
}
