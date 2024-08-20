package Services.Ausencia;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;

public class Folga extends Ausencia {

    public Folga() {
        super("FOLGA");
    }

    public void resetarDiasTrabalhados() {
        super.resetarDiasTrabalhados();
    }

    public void trabalharDia() {
        setDiasTrabalhadosConsecutivos(getDiasTrabalhadosConsecutivos() + 1);
    }

    public boolean podeTirarFolga() {
        return getDiasTrabalhadosConsecutivos() >= 4;
    }

    public void tirarFolga(Funcionario funcionario) {
        if (podeTirarFolga()) {
            LocalDate dataInicioFolga = LocalDate.now();
            setDataInicio(dataInicioFolga); // Define a data inicial da folga
            setDataFinal(dataInicioFolga.plusDays(1)); // Define a data final da folga (por exemplo, 1 dia de folga)
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " recebeu uma folga de " + getDataInicio() + " até " + getDataFinal());
        } else {
            System.out.println(funcionario.getNome() + " não cumpre os critérios para folga.");
        }
    }

    public LocalDate getDataInicioFolga() {
        return getDataInicio();
    }

    public LocalDate getDataFinalFolga() {
        return getDataFinal();
    }
}
