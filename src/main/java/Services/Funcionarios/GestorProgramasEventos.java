package Services.Funcionarios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GestorProgramasEventos extends Funcionario {

    private String nomeEvento;
    private LocalDateTime dataEvento;
    private String temaEvento;

    List<GestorProgramasEventos> listaEventos;

    public GestorProgramasEventos(){
        listaEventos = new LinkedList<>();
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTemaEvento() {
        return temaEvento;
    }

    public void setTemaEvento(String temaEvento) {
        this.temaEvento = temaEvento;
    }

    public void realizaEvento() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do Evento: ");
        nomeEvento = sc.nextLine();

        System.out.print("Data e Hora do Evento: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dataEvento = LocalDateTime.parse(sc.nextLine(), formatter);

        System.out.print("Tema do Evento: ");
        temaEvento = sc.nextLine();

        GestorProgramasEventos evento = new GestorProgramasEventos();
        evento.setNomeEvento(nomeEvento);
        evento.setDataEvento(dataEvento);
        evento.setTemaEvento(temaEvento);

        listaEventos.add(evento);
    }

    public void exibirEventos(){
        int i = 1;
        for(GestorProgramasEventos evento: listaEventos){
            System.out.println("\n"+i+"º EVENTO! ");
            System.out.println("Evento: " + evento.getNomeEvento());
            System.out.println("Data: " + evento.getDataEvento());
            System.out.println("Tema: " + evento.getTemaEvento()+"\n");
            i++;
        }
    }

    public void receberNotificacao(SegurancaProfissional incidente) {
        super.enviarNotificacaoIncidente(incidente);
    }
}
