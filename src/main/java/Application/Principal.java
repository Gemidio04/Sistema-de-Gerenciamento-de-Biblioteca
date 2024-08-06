package Application;

import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

public class Principal {

    public static void main(String[] args) {

        OperacoesBiblioteca op = new OperacoesBiblioteca();

        int contador = 0;
        while (contador < 2){
            op.registrarNovoCliente();
            System.out.println();
            contador++;
        }

        op.listaTodosClientes();
        op.listaClienteDadoUmNome("Gustavo");
    }
}


