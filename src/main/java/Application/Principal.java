package Application;

import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

public class Principal {

    public static void main(String[] args) {

        OperacoesBiblioteca op = new OperacoesBiblioteca();

        op.registrarNovoCliente();
        System.out.println();

        op.listaTodosClientes();
        op.listaClienteDadoUmNome("Gustavo");
    }
}


