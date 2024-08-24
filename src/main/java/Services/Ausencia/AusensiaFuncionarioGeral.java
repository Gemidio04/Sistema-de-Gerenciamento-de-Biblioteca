package Services.Ausencia;

import Services.ENUM.AusenciaENUM;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AusensiaFuncionarioGeral extends Ausencia {
    private int idAusenciaFuncionarioGeral;
    private int idFuncionarioGeral;
    private Connection connection;

    public AusensiaFuncionarioGeral() {
    }

    public int getIdAusenciaFuncionarioGeral() {
        return idAusenciaFuncionarioGeral;
    }

    public void setIdAusenciaFuncionarioGeral(int idAusenciaFuncionarioGeral) {
        this.idAusenciaFuncionarioGeral = idAusenciaFuncionarioGeral;
    }

    public int getIdFuncionarioGeral() {
        return idFuncionarioGeral;
    }
    public void setIdFuncionarioGeral(int idFuncionarioGeral) {
        this.idFuncionarioGeral = idFuncionarioGeral;
    }

    public static AusensiaFuncionarioGeral instanciaAusensiaFuncionarioGeralSellectById(ResultSet resultSet) throws SQLException {
        AusensiaFuncionarioGeral ausensiaFuncionarioGeral = new AusensiaFuncionarioGeral();
        FuncionarioGeral funcionarioGeral = new FuncionarioGeral();
        ausensiaFuncionarioGeral.setIdAusenciaFuncionarioGeral(resultSet.getInt("idAusenciaFuncionarioGeral"));
        ausensiaFuncionarioGeral.setIdFuncionarioGeral(resultSet.getInt("idFuncionarioGeral"));
        return ausensiaFuncionarioGeral;
    }

    public static void instanciaAusensiaFuncionarioGeralSellect(ResultSet resultSet, List<String> listaAusenciaFuncionarioGeral) throws SQLException {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("idAusenciaFuncionarioGeral: ").append(resultSet.getInt("idAusenciaFuncionarioGeral")).append(",\n");
        sb.append("idFuncionarioGeral: ").append(resultSet.getInt("idFuncionarioGeral")).append(",\n");
        sb.append("tipoAusencia: ").append(resultSet.getString("tipoAusencia")).append(",\n");
        sb.append("DataInicio: ").append(resultSet.getString("dataInicio")).append(",\n");
        sb.append("DataFinal: ").append(resultSet.getString("dataFinal")).append(".\n");
        listaAusenciaFuncionarioGeral.add(sb.toString());
    }

    public static AusensiaFuncionarioGeral criarDadosParaInserir
            (int idAusenciaFuncionarioGeral, int idFuncionarioGeral, AusenciaENUM tipoAusencia, String dataInicio, String dataFinal){
          AusensiaFuncionarioGeral ausensiaFuncionarioGeral = new AusensiaFuncionarioGeral();
          ausensiaFuncionarioGeral.setIdAusenciaFuncionarioGeral(idAusenciaFuncionarioGeral);
          ausensiaFuncionarioGeral.setIdFuncionarioGeral(idFuncionarioGeral);
          ausensiaFuncionarioGeral.setTipoAusencia(tipoAusencia);
          ausensiaFuncionarioGeral.setDataInicio(dataInicio);
          ausensiaFuncionarioGeral.setDataFinal(dataFinal);
        return ausensiaFuncionarioGeral;
    }
}
