package Services.Ausencia;

import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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

    // MÉTODO QUE LIDA COM AS DATAS E EXIBIÇÕES:
    private void processarFerias(Funcionario funcionario) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Solicita a data inicial das férias ao usuário
            System.out.print("Digite a data inicial das férias (dd/MM/yyyy): ");
            String dataInicioInput = scanner.nextLine();
            LocalDate dataInicioFerias = LocalDate.parse(dataInicioInput, formatter);

            // Solicita a data final das férias ao usuário
            System.out.print("Digite a data final das férias (dd/MM/yyyy): ");
            String dataFimInput = scanner.nextLine();
            LocalDate dataFimFerias = LocalDate.parse(dataFimInput, formatter);

            // Verifica se a data final é posterior à data inicial
            if (dataFimFerias.isBefore(dataInicioFerias)) {
                System.out.println("A data final não pode ser anterior à data inicial. Tente novamente.");
                return;
            }

            // Adiciona a data inicial ao histórico de férias:
            registrarFerias(dataInicioFerias);

            // Armazena as datas no formato ISO (YYYY-MM-DD) no sistema:
            setDataInicio(dataInicioFerias.toString());
            setDataFinal(dataFimFerias.toString());
            resetarDiasTrabalhados();

            System.out.println(
                    funcionario.getNome() +
                    " está de férias de "
                    + dataInicioFerias.format(formatter)
                    + " até " + dataFimFerias.format(formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Por favor, use o formato dd/MM/yyyy.");
        }
    }

    public void verificaSePodeTirarFerias(Funcionario funcionario) {
        // Método que verifica se o funcionário pode tirar férias e chama o método para processar as datas
        if (podeTirarFerias()) {
            processarFerias(funcionario);
        } else {
            System.out.println(funcionario.getNome() + " já tirou férias este ano!");
        }
    }

    public String getDataInicioFerias() {
        return getDataInicio();
    }

    public String getDataFinalFerias() {
        return getDataFinal();
    }
}
