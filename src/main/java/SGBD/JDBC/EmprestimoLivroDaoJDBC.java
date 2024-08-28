package SGBD.JDBC;

import Livros.EmprestimoLivro;
import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.EmprestimoLivroDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoLivroDaoJDBC extends ConexaoDAO implements EmprestimoLivroDAO {

    public EmprestimoLivroDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(EmprestimoLivro emprestimoLivro) {
        PreparedStatement preparedStatement = null;

        try{

            preparedStatement = connection.prepareStatement
            ("INSERT INTO Emprestimo_Livro VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1,emprestimoLivro.getIdEmprestimoLivro());
            preparedStatement.setString(2, emprestimoLivro.getIsbn());
            preparedStatement.setInt(3, emprestimoLivro.getIdCliente());
            preparedStatement.setString(4, emprestimoLivro.getDataEmprestimo());
            preparedStatement.setString(5, emprestimoLivro.getDataDevolucaoEmprestimo());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }

    }

    @Override
    public void update(EmprestimoLivro emprestimoLivro) {
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement
                    ("UPDATE Emprestimo_Livro SET "+
                          "idEmprestimoLivro = ?, isbn = ?, idCliente = ?, " +
                          "dataEmprestimo = ?, dataDevolucaoEmprestimo = ? " +
                          "WHERE idEmprestimoLivro = ?");

            preparedStatement.setInt(1, emprestimoLivro.getIdEmprestimoLivro());
            preparedStatement.setString(2, emprestimoLivro.getIsbn());
            preparedStatement.setInt(3, emprestimoLivro.getIdCliente());
            preparedStatement.setString(4, emprestimoLivro.getDataEmprestimo());
            preparedStatement.setString(5, emprestimoLivro.getDataDevolucaoEmprestimo());
            preparedStatement.setInt(6, emprestimoLivro.getIdEmprestimoLivro());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE REALIZADO!");
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer idEmprestimoLivro) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Emprestimo_Livro WHERE idEmprestimoLivro = ?");
            preparedStatement.setInt(1, idEmprestimoLivro);
            int linhas = preparedStatement.executeUpdate();

            if (linhas == 0)
                throw new DBException("O idEmprestimoLivro fornecido não existe!");
            System.out.println("DELETE REALIZADO!");
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement
                    ("SELECT EL.idEmprestimoLivro, EL.isbn, EL.idCliente, DATE_FORMAT(EL.dataEmprestimo, '%d/%m/%Y') AS dataEmprestimo, " +
                    "DATE_FORMAT(EL.dataDevolucaoEmprestimo, '%d/%m/%Y') AS dataDevolucaoEmprestimo FROM EMPRESTIMO_LIVRO EL");
            resultSet = preparedStatement.executeQuery();

            List<EmprestimoLivro> emprestimoLivros = new ArrayList<>();

            while (resultSet.next()){
                EmprestimoLivro emprestimoLivro = EmprestimoLivro.instanciaEmprestimoLivro(resultSet);
                emprestimoLivros.add(emprestimoLivro);
            }
            System.out.print("Empréstimos Livros: ");
            return emprestimoLivros;
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }

}
