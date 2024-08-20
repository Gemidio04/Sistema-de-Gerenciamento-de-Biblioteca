package Services.Ausencia;

import Services.ENUM.AUSENCIA;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AusensiaFuncionarioGeral extends Ausencia {
    private int idAusenciaFuncionarioGeral;
    private int idFuncionarioGeral;
    private Connection connection;

    public AusensiaFuncionarioGeral(){
    }

    public AusensiaFuncionarioGeral(Connection connection){
        this.idAusenciaFuncionarioGeral = 1;
        this.connection = connection;
    }

    public void setIdAusenciaFuncionarioGeral(int idAusenciaFuncionarioGeral) {
        this.idAusenciaFuncionarioGeral = idAusenciaFuncionarioGeral;
    }

    public void setIdFuncionarioGeral(int idFuncionarioGeral) {
        this.idFuncionarioGeral = idFuncionarioGeral;
    }

    public static AusensiaFuncionarioGeral instanciaAusensiaFuncionarioGeral(ResultSet resultSet) throws SQLException {
        AusensiaFuncionarioGeral ausenciaFuncionarioGeral = new AusensiaFuncionarioGeral();
        FuncionarioGeral funcionarioGeral = new FuncionarioGeral();
        ausenciaFuncionarioGeral.setIdAusenciaFuncionarioGeral(resultSet.getInt("idAusenciaFuncionarioGeral"));
        ausenciaFuncionarioGeral.setIdFuncionarioGeral(resultSet.getInt("idFuncionarioGeral"));
        String tipoAusensiaString = resultSet.getString("tipoAusencia");
        AUSENCIA tipoAusensia = AUSENCIA.valueOf(Ausencia.fromString(tipoAusensiaString));
        ausenciaFuncionarioGeral.setTipoAusencia(tipoAusensia);
        ausenciaFuncionarioGeral.setDataInicio(resultSet.getDate("dataInicio").toLocalDate());
        ausenciaFuncionarioGeral.setDataFinal(resultSet.getDate("dataFinal").toLocalDate());
        return ausenciaFuncionarioGeral;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AusensiaFuncionarioGeral{").append("\n");
        sb.append("idAusenciaFuncionarioGeral: ").append(idAusenciaFuncionarioGeral).append(".\n");
        sb.append("idFuncionarioGeral: ").append(idFuncionarioGeral).append(".\n");
        sb.append("tipo de Ausência: ").append(getTipoAusencia()).append(".\n");
        sb.append("Data de Início: ").append(getDataInicio()).append(".\n");
        sb.append("Data de Final: ").append(getDataFinal()).append(".\n");
        sb.append('}');
        return sb.toString();
    }
}
