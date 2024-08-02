package Services.Funcionarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GestorProgramasEventos extends Funcionario {

    private String nomeEvento;
    private LocalDate dataEvento;
    private String temaEvento;

    List<GestorProgramasEventos> listaEventos = new LinkedList<>();

    public GestorProgramasEventos(){
        nomeEvento = null;
        dataEvento = null;
        temaEvento = null;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTemaEvento() {
        return temaEvento;
    }

    public void setTemaEvento(String temaEvento) {
        this.temaEvento = temaEvento;
    }

    public List<GestorProgramasEventos> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<GestorProgramasEventos> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public void realizaEvento() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Nome do Evento: ");
        nomeEvento = sc.nextLine();

        System.out.print("Data do Evento: ");
        dataEvento = LocalDate.parse(sc.nextLine(), formatter);

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
            System.out.println(i+"º EVENTO! ");
            System.out.println("Evento: " + evento.getNomeEvento());
            System.out.println("Data: " + evento.getDataEvento());
            System.out.println("Tema: " + evento.getTemaEvento()+"\n");
            i++;
        }
    }
}
