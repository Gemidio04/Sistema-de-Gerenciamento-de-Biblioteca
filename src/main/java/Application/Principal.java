package Application;

import Services.Funcionarios.GestorProgramasEventos;

public class Principal {

    public static void main(String[] args) {

        GestorProgramasEventos gestor = new GestorProgramasEventos();

        int contador = 0;
        while (contador < 3){
            gestor.realizaEvento();
            contador++;
        }

        gestor.exibirEventos();
    }
}

import java.time.LocalDate;
import java.time.Month;

public class Principal {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        Month mesAtual = hoje.getMonth();
        int mesAtualInt = mesAtual.getValue(); // Se você precisar do valor numérico do mês
        System.out.println("Mês atual: " + mesAtual);
        System.out.println("Mês atual (número): " + mesAtualInt);
    }
}
