package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.SegurancaProfissional;
import Services.Regras.Regra;

public class Bibliotecaria extends OperacoesBiblioteca {
    private Regra regra;

    public void adicionarLivro(String isbn, Livro livro) {
        super.adicionarLivro(isbn, livro);
    }

    public void removerLivro(String isbn) {
        super.removerLivro(isbn);
    }

    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        super.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }
    public void venderLivro(String isbn) {
        super.venderLivro(isbn);
    }

    public void emprestarLivro(String isbn) {
        super.emprestarLivro(isbn);
    }

    public void devolverLivro(String isbn, Livro livro) {
        super.devolverLivro(isbn, livro);
    }

    public boolean checarDisponibilidade() {
        return super.checarDisponibilidade();
    }

    public Livro buscarLivroIsbn(String isbn) {
        return super.buscarLivroIsbn(isbn);
    }

    public Livro buscarLivroAutor(String autor) {
        return super.buscarLivroAutor(autor);
    }

    public Livro buscarLivroTitulo(String titulo) {
        return super.buscarLivroTitulo(titulo);
    }

    public void demitirAssistente(Funcionario funcionario) {
        if (regra.getQuantidadeAdvertencias() == 3 || regra.getAdvertencia()) {
            System.out.println("Assistente: " + funcionario.getNome() + " demitido!");
        }
    }

    @Override
    public void verificandoTodoSistemaBiblioteca(){
        super.verificandoTodoSistemaBiblioteca();
    }

    @Override
    public void receberNotificacao(SegurancaProfissional incidente) {
        super.receberNotificacao(incidente);
    }
}
