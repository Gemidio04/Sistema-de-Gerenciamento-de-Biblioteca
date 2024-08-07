package Services.Ausencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ausencia {
    protected int diasTrabalhadosConsecutivos;
    private List<LocalDate> historicoFerias;

    public Ausencia() {
        this.historicoFerias = new ArrayList<>();
        this.diasTrabalhadosConsecutivos = 0;
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
}
