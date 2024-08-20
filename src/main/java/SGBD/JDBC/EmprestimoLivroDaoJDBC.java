package SGBD.JDBC;

import Clientes.Cliente;
import Livros.EmprestimoLivro;
import Livros.Livro;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.EmprestimoLivroDAO;
import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmprestimoLivroDaoJDBC extends ConexaoDAO implements EmprestimoLivroDAO {

    public EmprestimoLivroDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(EmprestimoLivro emprestimoLivro) {

    }

    @Override
    public void update(EmprestimoLivro emprestimoLivro) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public EmprestimoLivro selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT idEmprestimoLivro, isbn, idCliente, dataEmprestimo, dataDevolucaoEmprestimo FROM" +
                    " Emprestimo_livro WHERE idEmprestimoLivro = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return EmprestimoLivro.instanciaEmprestimoLivro(resultSet);
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
    public List<EmprestimoLivro> selectAll() {
        return List.of();
    }

}
