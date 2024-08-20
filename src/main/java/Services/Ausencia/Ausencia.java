package Services.Ausencia;

import Services.ENUM.AUSENCIA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ausencia {
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private AUSENCIA tipoAusencia;
    private int diasTrabalhadosConsecutivos;
    private final List<LocalDate> historicoFerias;

    public Ausencia(String argumento){
        this.historicoFerias = new ArrayList<>();
        System.out.println(argumento);
    }

    public Ausencia() {
        this.historicoFerias = new ArrayList<>();
        this.diasTrabalhadosConsecutivos = 0;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
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


//    public void setTipoAusencia(AUSENCIA tipoAusencia) {
//        if (tipoAusencia == AUSENCIA.FOLGA) {
//            tipoAusencia = AUSENCIA.FOLGA;
//        } else {
//            tipoAusencia = AUSENCIA.FERIAS;
//        }
//    }

    public AUSENCIA getTipoAusencia() {
        return tipoAusencia;
    }

    public void setTipoAusencia(AUSENCIA tipoAusencia) {
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
            case "FOLGA" -> tipoAusenciaString = String.valueOf(AUSENCIA.FOLGA);
            case "FERIAS" -> tipoAusenciaString = String.valueOf(AUSENCIA.FERIAS);
            default -> throw new IllegalArgumentException("Tipo de ausência inválido: " + tipoAusenciaString);
        };
    }

}
