package Services.Ausencia;

import Services.ENUM.AusenciaENUM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AusensiaFuncionarioAdministrativo extends Ausencia {
    private int idAusenciaFuncionarioAdministrativo;
    private int idFuncionarioAdministrativo;
    private Connection connection;

    public AusensiaFuncionarioAdministrativo(){
    }

    public AusensiaFuncionarioAdministrativo(int idAusenciaFuncionarioGeral, int idFuncionarioGeral, AusenciaENUM ausencia, String dataInicio, String dataFinal) {
        this.connection = null;
        this.idAusenciaFuncionarioAdministrativo = idAusenciaFuncionarioGeral;
        this.idFuncionarioAdministrativo = idFuncionarioGeral;
        ausencia = getTipoAusencia();
        dataInicio = getDataInicio();
        dataFinal = getDataFinal();
    }

    public AusensiaFuncionarioAdministrativo(Connection connection){
        this.idAusenciaFuncionarioAdministrativo = 1;
        this.connection = connection;
    }

    public int getIdAusenciaFuncionarioAdministrativo() {
        return idAusenciaFuncionarioAdministrativo;
    }

    public void setIdAusenciaFuncionarioAdministrativo(int idAusenciaFuncionarioAdministrativo) {
        this.idAusenciaFuncionarioAdministrativo = idAusenciaFuncionarioAdministrativo;
    }

    public int getIdFuncionarioAdministrativo() {
        return idFuncionarioAdministrativo;
    }

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public static AusensiaFuncionarioAdministrativo instanciaAusensiaFuncionarioAdministrativoSellectById(ResultSet resultSet) throws SQLException {
        AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo = new AusensiaFuncionarioAdministrativo();
        ausensiaFuncionarioAdministrativo.setIdAusenciaFuncionarioAdministrativo(resultSet.getInt("idAusenciaFuncionarioAdministrativo"));
        ausensiaFuncionarioAdministrativo.setIdFuncionarioAdministrativo(resultSet.getInt("idFuncionarioAdministrativo"));
        return ausensiaFuncionarioAdministrativo;
    }

    public static void instanciaAusensiaFuncionarioAdministrativoSellectAll(ResultSet resultSet, List<String> listaAusenciaFuncionarioGeral) throws SQLException {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("idAusenciaFuncionarioAdministrativo: ").append(resultSet.getInt("idAusenciaFuncionarioAdministrativo")).append(",\n");
        sb.append("idFuncionarioAdministrativo: ").append(resultSet.getInt("idFuncionarioAdministrativo")).append(",\n");
        sb.append("tipoAusencia: ").append(resultSet.getString("tipoAusencia")).append(",\n");
        sb.append("DataInicio: ").append(resultSet.getString("dataInicio")).append(",\n");
        sb.append("DataFinal: ").append(resultSet.getString("dataFinal")).append(".\n");
        listaAusenciaFuncionarioGeral.add(sb.toString());
    }

    public static AusensiaFuncionarioAdministrativo criarDadosParaInserir
            (int idAusenciaFuncionarioAdministrativo, int idFuncionarioAdministrativo, AusenciaENUM tipoAusencia, String dataInicio, String dataFinal){
        AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo = new AusensiaFuncionarioAdministrativo();
        ausensiaFuncionarioAdministrativo.setIdAusenciaFuncionarioAdministrativo(idAusenciaFuncionarioAdministrativo);
        ausensiaFuncionarioAdministrativo.setIdFuncionarioAdministrativo(idFuncionarioAdministrativo);
        ausensiaFuncionarioAdministrativo.setTipoAusencia(tipoAusencia);
        ausensiaFuncionarioAdministrativo.setDataInicio(dataInicio);
        ausensiaFuncionarioAdministrativo.setDataFinal(dataFinal);
        return ausensiaFuncionarioAdministrativo;
    }
}
