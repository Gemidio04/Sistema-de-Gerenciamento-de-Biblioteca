package Services.Ausencia;

import Services.ENUM.AusenciaENUM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ausencia {
    private AusenciaENUM tipoAusencia;
    private String dataInicio;
    private String dataFinal;
    private int diasTrabalhadosConsecutivos;
    private final List<LocalDate> historicoFerias;

    public Ausencia() {
        this.historicoFerias = new ArrayList<>();
        this.diasTrabalhadosConsecutivos = 0;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<LocalDate> getHistoricoFerias() {
        return historicoFerias;
    }

    public int getDiasTrabalhadosConsecutivos() {
        return diasTrabalhadosConsecutivos;
    }

    public void setDiasTrabalhadosConsecutivos(int diasTrabalhadosConsecutivos) {
        this.diasTrabalhadosConsecutivos = diasTrabalhadosConsecutivos;
    }

    public void resetarDiasTrabalhados() {
        diasTrabalhadosConsecutivos = 0;
    }


    public AusenciaENUM getTipoAusencia() {
        return tipoAusencia;
    }

    public void setTipoAusencia(AusenciaENUM tipoAusencia) {
        this.tipoAusencia = tipoAusencia; // Directly set the value
    }

    public static String fromString(String tipoAusenciaString) {
//        switch (tipoAusenciaString.toUpperCase()) {
//            case "FOLGA":
//                return new Folga();
//            case "FERIAS":
//                return new Ferias();
//            default:
//                throw new IllegalArgumentException("Tipo de ausência inválido: " + tipoAusenciaString);
        return switch (tipoAusenciaString.toUpperCase()) {
            case "FOLGA" -> tipoAusenciaString = String.valueOf(AusenciaENUM.FOLGA);
            case "FERIAS" -> tipoAusenciaString = String.valueOf(AusenciaENUM.FERIAS);
            default -> throw new IllegalArgumentException("Tipo de ausência inválido: " + tipoAusenciaString);
        };
    }

}
