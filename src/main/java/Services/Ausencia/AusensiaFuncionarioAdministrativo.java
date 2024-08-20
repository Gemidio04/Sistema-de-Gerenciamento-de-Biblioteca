package Services.Ausencia;

import Services.ENUM.AUSENCIA;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AusensiaFuncionarioAdministrativo extends Ausencia {
    private int idAusenciaFuncionarioAdministrativo;
    private int idFuncionarioAdministrativo;
    private Connection connection;

    public AusensiaFuncionarioAdministrativo(){
    }

    public AusensiaFuncionarioAdministrativo(Connection connection){
        this.idAusenciaFuncionarioAdministrativo = 1;
        this.connection = connection;
    }

    public void setIdAusenciaFuncionarioAdministrativo(int idAusenciaFuncionarioAdministrativo) {
        this.idAusenciaFuncionarioAdministrativo = idAusenciaFuncionarioAdministrativo;
    }

    public void setIdFuncionarioAdministrativo(int idFuncionarioAdministrativo) {
        this.idFuncionarioAdministrativo = idFuncionarioAdministrativo;
    }

    public static AusensiaFuncionarioAdministrativo instanciaAusensiaFuncionarioAdministrativo(ResultSet resultSet) throws SQLException {
        AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo = new AusensiaFuncionarioAdministrativo();
        ausensiaFuncionarioAdministrativo.setIdAusenciaFuncionarioAdministrativo(resultSet.getInt("idAusenciaFuncionarioAdministrativo"));
        ausensiaFuncionarioAdministrativo.setIdFuncionarioAdministrativo(resultSet.getInt("idFuncionarioAdministrativo"));
        String tipoAusensiaString = resultSet.getString("tipoAusencia");
        AUSENCIA tipoAusensia = AUSENCIA.valueOf(Ausencia.fromString(tipoAusensiaString));
        ausensiaFuncionarioAdministrativo.setTipoAusencia(tipoAusensia);
        ausensiaFuncionarioAdministrativo.setDataInicio(resultSet.getDate("dataInicio").toLocalDate());
        ausensiaFuncionarioAdministrativo.setDataFinal(resultSet.getDate("dataFinal").toLocalDate());
        return ausensiaFuncionarioAdministrativo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AusensiaFuncionarioAdministrativo{").append(".\n");
        sb.append("idAusenciaFuncionarioAdministrativo: ").append(idAusenciaFuncionarioAdministrativo).append(".\n");
        sb.append("idFuncionarioAdministrativo: ").append(idFuncionarioAdministrativo).append(".\n");
        sb.append("tipo de Ausência: ").append(getTipoAusencia()).append(".\n");
        sb.append("Data de Início: ").append(getDataInicio()).append(".\n");
        sb.append("Data de Final: ").append(getDataFinal()).append(".\n");
        sb.append('}');
        return sb.toString();
    }
}
