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
