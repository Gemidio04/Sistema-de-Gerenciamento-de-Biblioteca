package Services.Funcionarios;

import Services.ENUM.Incidentes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SegurancaProfissional extends Funcionario {
    private int numeroIncidente;
    private int tipo;
    private String descricao;
    private LocalDateTime dataHora;
    private String responsavel;
    private List<String> logIncidentes;

    public SegurancaProfissional() {
        this.numeroIncidente = 0;
        logIncidentes = new ArrayList<>();
    }

    public SegurancaProfissional(int tipo, String descricao, LocalDateTime dataHora, String responsavel) {
        this.numeroIncidente = 0;
        this.numeroIncidente = 0;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.responsavel = responsavel;
        logIncidentes = new ArrayList<>();
    }

    protected void registrarIncidente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nTipos de incidentes:");
        for (Incidentes incidente : Incidentes.values()) {
            System.out.println(incidente.ordinal() + 1 + ". " + incidente);
        }

        System.out.print("\nDigite o Número do Incidente: ");

        while (!sc.hasNextInt()) {
            System.out.println("\nSeleção inválida. Por favor, digite um número inteiro.");
            sc.next(); // Consome a entrada inválida
            System.out.print("Digite o Número do Incidente: ");
        }

        tipo = sc.nextInt();

        while (tipo < 1 || tipo > Incidentes.values().length) {
            System.out.println("\nSeleção inválida. O número deve estar entre 1 e " + Incidentes.values().length + ".");
            System.out.print("Digite Número do Incidente: ");
            tipo = sc.nextInt();
        }
        numeroIncidente++;

        sc.nextLine(); // Consome a nova linha pendente

        System.out.print("Descreva o incidente: ");
        descricao = sc.nextLine();

        System.out.print("Descreva a data e hora do incidente (dd/MM/yyyy HH:mm): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dataHora = LocalDateTime.parse(sc.nextLine(), formatter);

        System.out.print("Digite o Nome do Responsável (Se não souber digite 'DESCONHECIDO'): ");
        responsavel = sc.nextLine();

        SegurancaProfissional incidente = new SegurancaProfissional(tipo, descricao, dataHora, responsavel);
        logIncidentes.add(String.valueOf(incidente));

        System.out.println("Incidente registrado com sucesso!");

        enviarNotificacao(incidente);
    }

    protected void enviarNotificacao(SegurancaProfissional incidente) {
        System.out.println("\nOCORREU UM INCIDENTE:");
        System.out.println("DETALHES:");
        System.out.println(incidente);
    }

    @Override
    public String toString() {
        return "Incidentes{" + "\n" +
                "Incidente Número = " + tipo + ", \n" +
                "Tipo = " + tipo + ", \n" +
                "Descricao = " + descricao + ", \n" +
                "Data e Hora = " + dataHora + ", \n" +
                "Responsavel: " + responsavel +
                '}';
    }

}
