package Services.FuncionarioIndisponivel;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;

public class Ferias extends Ausencia {

    public void resetarDiasTrabalhados() {
        super.resetarDiasTrabalhados();
    }

    public boolean podeTirarFerias() {
        LocalDate agora = LocalDate.now();
        long feriasEsteAno = getHistoricoFerias().stream()
                .filter(data -> data.getYear() == agora.getYear())
                .count();
        return feriasEsteAno == 0;
    }

    public void tirarFerias(Funcionario funcionario) {
        if (podeTirarFerias()) {
            getHistoricoFerias().add(LocalDate.now());
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " está de férias.");
        } else {
            System.out.println(funcionario.getNome() + " já tirou férias este ano.");
        }
    }
}
