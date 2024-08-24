package Services.Ausencia;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ferias extends Ausencia {

    private LocalDate agora;

    public Ferias(){
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate agora = LocalDate.parse(funcionario.getDataContratacao(), formatter);

        if (podeTirarFerias()) {
            getHistoricoFerias().add(agora); // Adiciona a data atual ao histórico de férias
            setDataInicio(String.valueOf(agora)); // Define a data inicial das férias
            setDataFinal(String.valueOf(agora.plusDays(30))); // Define a data final das férias (por exemplo, 30 dias de férias)
            resetarDiasTrabalhados();
            System.out.println(funcionario.getNome() + " está de férias de " + getDataInicio() + " até " + getDataFinal());
        } else {
            System.out.println(funcionario.getNome() + " já tirou férias este ano.");
        }
    }

    public String getDataInicioFerias() {
        return getDataInicio();
    }

    public String getDataFinalFerias() {
        return getDataFinal();
    }
}
