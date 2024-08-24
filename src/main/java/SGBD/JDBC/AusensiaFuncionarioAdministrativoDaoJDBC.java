package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.AusenciaDAO;
import SGBD.InterfacesDAO.AusenciaFuncionarioAdministrativoDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import SGBD.InterfacesDAO.AusensiaFuncionarioGeralDAO;
import Services.Ausencia.Ausencia;
import Services.Ausencia.AusensiaFuncionarioAdministrativo;
import Services.ENUM.AusenciaENUM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AusensiaFuncionarioAdministrativoDaoJDBC extends ConexaoDAO implements AusenciaDAO, AusenciaFuncionarioAdministrativoDAO {

    public AusensiaFuncionarioAdministrativoDaoJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Ausencia_Funcionario_Administrativo" +
                            " VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, ausensiaFuncionarioAdministrativo.getIdAusenciaFuncionarioAdministrativo());
            preparedStatement.setInt(2, ausensiaFuncionarioAdministrativo.getIdFuncionarioAdministrativo());

            // Define um valor padrão caso tipoAusencia seja nulo
            AusenciaENUM tipoAusencia = ausensiaFuncionarioAdministrativo.getTipoAusencia() != null ?
                    ausensiaFuncionarioAdministrativo.getTipoAusencia() :
                    AusenciaENUM.FOLGA;

            preparedStatement.setString(3, tipoAusencia.name());
            preparedStatement.setString(4, ausensiaFuncionarioAdministrativo.getDataInicio());
            preparedStatement.setString(5, ausensiaFuncionarioAdministrativo.getDataFinal());
            preparedStatement.executeUpdate();
            System.out.println("INSERT REALIZADO!");

        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Ausencia ausencia) {
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public AusensiaFuncionarioAdministrativo selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Ausencia_Funcionario_Administrativo " +
                     "WHERE idAusenciaFuncionarioAdministrativo = ? ");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return AusensiaFuncionarioAdministrativo.instanciaAusensiaFuncionarioAdministrativoSellectById(resultSet);
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
    public List<String> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
            "SELECT AFA.idAusenciaFuncionarioAdministrativo , AFA.idFuncionarioAdministrativo, " +
            "AFA.tipoAusencia, DATE_FORMAT(AFA.dataInicio, '%d/%m/%Y') AS dataInicio, " +
            "DATE_FORMAT(AFA.dataFinal, '%d/%m/%Y') AS dataFinal FROM Ausencia_Funcionario_Administrativo AFA;");
            resultSet = preparedStatement.executeQuery();

            List<String> listaAusenciaFuncionarioGeral = new ArrayList<>();

            while (resultSet.next()) {
                AusensiaFuncionarioAdministrativo.
                instanciaAusensiaFuncionarioAdministrativoSellectAll(resultSet, listaAusenciaFuncionarioGeral);
            }
            System.out.print("Ausencias Funcionários Administrativo: ");
            return listaAusenciaFuncionarioGeral;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }
}
