package Services.FuncionarioIndisponivel;

import Services.Funcionarios.Funcionario;

public class Folga extends Ausencia {

    public void trabalharDia() {
       setDiasTrabalhadosConsecutivos(getDiasTrabalhadosConsecutivos() + 1);
    }


    public void resetarDiasTrabalhados() {
        super.resetarDiasTrabalhados();
    }


    public boolean podeTirarFolga() {
        return diasTrabalhadosConsecutivos >= 4;
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
