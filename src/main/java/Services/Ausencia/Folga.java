package Services.Ausencia;

import Services.Funcionarios.Funcionario;

public class Folga extends Ausencia {

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
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " recebeu uma folga.");
        } else {
            System.out.println(funcionario.getNome() + " não cumpre os critérios para folga.");
        }
    }
}
