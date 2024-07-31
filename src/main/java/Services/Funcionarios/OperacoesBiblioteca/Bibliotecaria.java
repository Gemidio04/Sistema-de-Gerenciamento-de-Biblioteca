package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;


public class Bibliotecaria extends OperacoesBiblioteca {
    private boolean advertencia;
    private int quantidadeAdvertencias;
    private Assistente assistente;

    public Bibliotecaria(){
        advertencia = false;
        quantidadeAdvertencias = 0;
        assistente = new Assistente();
    }

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
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            setQuantidadeLivrosVendidos(getQuantidadeLivrosVendidos() + 1);
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    public void emprestarLivro(String isbn) {
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            setQuantidadeLivrosEmprestados(getQuantidadeLivrosEmprestados() + 1);
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    public void devolverLivro(String isbn, Livro livro) {
        adicionarLivro(isbn, livro);
    }

    public boolean checarDisponibilidade() {
        return getEstoque().getQuantidade() > 0;
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

    public void adicionarAdvertencia() {
        if(advertencia)
            quantidadeAdvertencias++;
    }

    public boolean verificarAdvertencias() {
        if(quantidadeAdvertencias == 3) {
            System.out.println("Número de advertências: " + quantidadeAdvertencias);
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
