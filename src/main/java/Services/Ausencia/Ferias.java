package Services.Ausencia;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;

public class Ferias extends Ausencia {

    private LocalDate agora;

    public Ferias(){
        super("FERIAS");
        agora = LocalDate.now();
    }

    public void resetarDiasTrabalhados() {
        super.resetarDiasTrabalhados();
    }

    public boolean podeTirarFerias() {
        long feriasEsteAno = getHistoricoFerias().stream()
                .filter(data -> data.getYear() == agora.getYear())
                .count();
        return feriasEsteAno == 0;
    }

    public void tirarFerias(Funcionario funcionario) {
        if (podeTirarFerias()) {
            getHistoricoFerias().add(agora); // Adiciona a data atual ao histórico de férias
            setDataInicio(agora); // Define a data inicial das férias
            setDataFinal(agora.plusDays(30)); // Define a data final das férias (por exemplo, 30 dias de férias)
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " está de férias de " + getDataInicio() + " até " + getDataFinal());
        } else {
            System.out.println(funcionario.getNome() + " já tirou férias este ano.");
        }
    }

    public LocalDate getDataInicioFerias() {
        return getDataInicio();
    }

    public LocalDate getDataFinalFerias() {
        return getDataFinal();
    }
}
