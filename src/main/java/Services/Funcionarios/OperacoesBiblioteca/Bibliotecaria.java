package Services.Funcionarios.OperacoesBiblioteca;

import Services.Funcionarios.Funcionario;


public class Bibliotecaria extends OperacoesBiblioteca {
    private boolean advertencia;
    private int quantidadeAdvertencias;

    public Bibliotecaria() {
        advertencia = false;
        quantidadeAdvertencias = 0;
    }

    public void adicionarAdvertencia() {
        quantidadeAdvertencias++;
    }

    public boolean verificarAdvertencias() {
        if(quantidadeAdvertencias == 3) {
            System.out.println("Número de Advertencias: " + quantidadeAdvertencias);
            return advertencia = true;
        }
        return false;
    }

    public void demitirAssistente(Funcionario funcionario) {
        if (verificarAdvertencias()) {
            System.out.println("Funcionário: " + funcionario.getNome() + " demitido!");

        }
    }


}
