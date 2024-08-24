package SGBD.JDBC;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.InterfacesDAO.AusenciaDAO;
import SGBD.InterfacesDAO.AusenciaFuncionarioAdministrativoDAO;
import SGBD.InterfacesDAO.AusensiaFuncionarioGeralDAO;
import SGBD.Connection.ConexaoDAO;
import SGBD.Exception.DBException;
import Services.Ausencia.Ausencia;
import Services.Ausencia.AusensiaFuncionarioAdministrativo;
import Services.Ausencia.AusensiaFuncionarioGeral;
import Services.ENUM.AusenciaENUM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AusensiaFuncionarioGeralDaoJDBC extends ConexaoDAO implements AusenciaDAO, AusensiaFuncionarioGeralDAO {

    public AusensiaFuncionarioGeralDaoJDBC(Connection connection) {
        super(connection);
    }

//    @Override
//    public void insert(AusensiaFuncionarioGeral ausensiaFuncionarioGeral) {
//        PreparedStatement preparedStatement = null;
//
//        try {
//            preparedStatement = connection.prepareStatement
//                    ("INSERT INTO Ausencia_Funcionario_Geral VALUES (?,?,?,?,?)");
//
//            preparedStatement.setInt(1, ausensiaFuncionarioGeral.getIdAusenciaFuncionarioGeral());
//            preparedStatement.setInt(2, ausensiaFuncionarioGeral.getIdFuncionarioGeral());
//            preparedStatement.setString(3, String.valueOf(ausensiaFuncionarioGeral.getTipoAusencia()));
//            preparedStatement.setString(4, ausensiaFuncionarioGeral.getDataInicio());
//            preparedStatement.setString(5, ausensiaFuncionarioGeral.getDataFinal());
//            preparedStatement.executeUpdate();
//            System.out.println("INSERT REALIZADO!");
//        }catch (SQLException ex){
//            throw new DBException(ex.getMessage());
//        }finally {
//            ConexaoBancoDeDados.closeStatement(preparedStatement);
//        }
//    }

    @Override
    public void insert(AusensiaFuncionarioGeral ausenciaFuncionarioGeral) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Ausencia_Funcionario_Geral" +
                            " VALUES (?, ?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, ausenciaFuncionarioGeral.getIdAusenciaFuncionarioGeral());
            preparedStatement.setInt(2, ausenciaFuncionarioGeral.getIdFuncionarioGeral());

            // Define um valor padrão caso tipoAusencia seja nulo
            AusenciaENUM tipoAusencia = ausenciaFuncionarioGeral.getTipoAusencia() != null ?
                    ausenciaFuncionarioGeral.getTipoAusencia() :
                    AusenciaENUM.FOLGA;

            preparedStatement.setString(3, tipoAusencia.name());
            preparedStatement.setString(4, ausenciaFuncionarioGeral.getDataInicio());
            preparedStatement.setString(5, ausenciaFuncionarioGeral.getDataFinal());
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
    public AusensiaFuncionarioGeral selectById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{

            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Ausencia_Funcionario_Geral WHERE idAusenciaFuncionarioGeral = ? ");

            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return AusensiaFuncionarioGeral.instanciaAusensiaFuncionarioGeralSellectById(resultSet);
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
                    "SELECT AFG.idAusenciaFuncionarioGeral, AFG.idFuncionarioGeral, " +
                    "AFG.tipoAusencia, DATE_FORMAT(AFG.dataInicio, '%d/%m/%Y') AS dataInicio, " +
                    "DATE_FORMAT(AFG.dataFinal, '%d/%m/%Y') AS dataFinal FROM Ausencia_Funcionario_Geral AFG");
            resultSet = preparedStatement.executeQuery();

            List<String> listaAusenciaFuncionarioGeral = new ArrayList<>();

            while (resultSet.next()) {
                AusensiaFuncionarioGeral.instanciaAusensiaFuncionarioGeralSellect(resultSet, listaAusenciaFuncionarioGeral);
            }
            System.out.print("Ausencias Funcionários Geral: ");
            return listaAusenciaFuncionarioGeral;
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        } finally {
            ConexaoBancoDeDados.closeStatement(preparedStatement);
            ConexaoBancoDeDados.closeResultSet(resultSet);
        }
    }

}
