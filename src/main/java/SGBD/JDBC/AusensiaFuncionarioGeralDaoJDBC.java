package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.DAO.AusenciaDAO;
import SGBD.DAO.AusensiaFuncionarioGeralDAO;
import SGBD.DAO.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Ausencia.Ausencia;
import Services.Ausencia.AusensiaFuncionarioGeral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AusensiaFuncionarioGeralDaoJDBC extends ConexaoDAO implements AusenciaDAO, AusensiaFuncionarioGeralDAO {

    public AusensiaFuncionarioGeralDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Ausencia ausencia) {

    }

    @Override
    public void update(Ausencia ausencia) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public AusensiaFuncionarioGeral selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Ausencia_Funcionario_Geral WHERE idAusenciaFuncionarioGeral = ? ");

            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return AusensiaFuncionarioGeral.instanciaAusensiaFuncionarioGeral(resultSet);
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
    public List<Ausencia> selectAll() {
        return List.of();
    }
}
