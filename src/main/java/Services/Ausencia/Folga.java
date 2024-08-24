package Services.Ausencia;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Folga extends Ausencia {

    public Folga() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioFolga = LocalDate.parse(funcionario.getDataContratacao(), formatter);

        if (podeTirarFolga()) {
            dataInicioFolga = LocalDate.now();
            setDataInicio(String.valueOf(dataInicioFolga)); // Define a data inicial da folga
            setDataFinal(String.valueOf(dataInicioFolga.plusDays(1))); // Define a data final da folga (por exemplo, 1 dia de folga)
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " recebeu uma folga de " + getDataInicio() + " até " + getDataFinal());
        } else {
            System.out.println(funcionario.getNome() + " não cumpre os critérios para folga.");
        }
    }

    public String getDataInicioFolga() {
        return getDataInicio();
    }

    public String getDataFinalFolga() {
        return getDataFinal();
    }
}
