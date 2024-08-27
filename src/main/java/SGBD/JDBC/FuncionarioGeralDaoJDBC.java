package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.FuncionarioDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.InterfacesDAO.FuncionarioGeralDAO;
import SGBD.Exception.DBException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioGeralDaoJDBC extends ConexaoDAO implements FuncionarioDAO, FuncionarioGeralDAO {

    public FuncionarioGeralDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(FuncionarioGeral funcionarioGeral) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Funcionario_Geral VALUES (?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, funcionarioGeral.getIdFuncionarioGeral());
            preparedStatement.setString(2, funcionarioGeral.getNome());
            preparedStatement.setString(3, funcionarioGeral.getEmail());
            preparedStatement.setString(4, funcionarioGeral.getCPF());
            preparedStatement.setString(5, funcionarioGeral.getTurno());
            preparedStatement.setString(6, funcionarioGeral.getDataContratacao());
            preparedStatement.setDouble(7, funcionarioGeral.getSalario());
            preparedStatement.setString(8, funcionarioGeral.getCargo().toString());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(FuncionarioGeral funcionarioGeral) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Funcionario_Geral SET " +
                        "idFuncionarioGeral = ?, nome = ?, email = ?, CPF = ?, " +
                        "turno = ?, dataContratacao = ?, salario = ?, cargo = ? " +
                        "WHERE idFuncionarioGeral = ?")) {

            preparedStatement.setInt(1, funcionarioGeral.getIdFuncionarioGeral());
            preparedStatement.setString(2, funcionarioGeral.getNome());
            preparedStatement.setString(3, funcionarioGeral.getEmail());
            preparedStatement.setString(4, funcionarioGeral.getCPF());
            preparedStatement.setString(5, funcionarioGeral.getTurno());
            preparedStatement.setString(6, funcionarioGeral.getDataContratacao());
            // Define salário ou NULL:
            preparedStatement.setObject(7, funcionarioGeral.getSalario(), java.sql.Types.DOUBLE);
            // Define cargo ou NULL:
            preparedStatement.setObject(8, funcionarioGeral.getCargo() != null ?
                    funcionarioGeral.getCargo().name() : null, java.sql.Types.VARCHAR);
            preparedStatement.setInt(9, funcionarioGeral.getIdFuncionarioGeral());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE REALIZADO!");
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public FuncionarioGeral selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Funcionario_Geral WHERE idFuncionarioGeral = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return FuncionarioGeral.instanciaFuncionarioGeral(resultSet);
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
    public List<Funcionario> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement
                    ("SELECT FG.idFuncionarioGeral, FG.nome, FG.email, FG.CPF, FG.turno, " +
                    "DATE_FORMAT(FG.dataContratacao, '%d/%m/%Y') AS dataContratacao, FG.salario, " +
                    "FG.cargo FROM FUNCIONARIO_GERAL FG ");

            resultSet = preparedStatement.executeQuery();

            List<Funcionario> listaFuncionariosGerais = new ArrayList<>();

            while (resultSet.next()){
                FuncionarioGeral funcionarioGeral = FuncionarioGeral.instanciaFuncionarioGeral(resultSet);
                Funcionario funcionario = Funcionario.instanciaFuncionario(resultSet);
                listaFuncionariosGerais.add(funcionarioGeral);
                listaFuncionariosGerais.add(funcionario);
            }
            System.out.print("Funcionários Gerais: ");
            return listaFuncionariosGerais;
        }catch (SQLException ex){
            throw new DBException(ex.getMessage());
        }finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }
}
