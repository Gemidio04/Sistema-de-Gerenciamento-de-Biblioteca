package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;

import java.time.LocalDate;
import java.time.LocalTime;

public class OperacoesBiblioteca extends Funcionario {
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;

    public OperacoesBiblioteca(){
        super();
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
    }

    public int getQuantidadeLivrosVendidos() {
        return quantidadeLivrosVendidos;
    }

    public void setQuantidadeLivrosVendidos(int quantidadeLivrosVendidos) {
        this.quantidadeLivrosVendidos = quantidadeLivrosVendidos;
    }

    public int getQuantidadeLivrosEmprestados() {
        return quantidadeLivrosEmprestados;
    }

    public void setQuantidadeLivrosEmprestados(int quantidadeLivrosEmprestados) {
        this.quantidadeLivrosEmprestados = quantidadeLivrosEmprestados;
    }

    public void adicionarLivro(String isbn, Livro livro) {
        getLivros().put(isbn, livro);
        getEstoque().setQuantidade(getEstoque().getQuantidade() + 1);
    }

    public void removerLivro(String isbn) {
        getLivros().remove(isbn);
        if(getEstoque().getQuantidade() > 0)
            getEstoque().setQuantidade(getEstoque().getQuantidade() - 1);
        else
            throw new ValidacaoException("Não há mais unidades deste livro!");
    }

    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        Livro livro = getLivros().get(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAnoPublicacao);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
    }
    public void venderLivro(String isbn) {
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    public void emprestarLivro(String isbn) {
        if (checarDisponibilidade()) {
            removerLivro(isbn);
            quantidadeLivrosEmprestados++;
        } else {
            throw new ValidacaoException
                    ("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    public void devolverLivro(String isbn, Livro livro) {
        adicionarLivro(isbn, livro);
    }

    boolean checarDisponibilidade() {
        return getEstoque().getQuantidade() > 0;
    }

    public Livro buscarLivroIsbn(String isbn) {
        Livro livro = getLivros().get(isbn);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    public Livro buscarLivroAutor(String autor) {
        Livro livro = getLivros().get(autor);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    public Livro buscarLivroTitulo(String titulo) {
        Livro livro = getLivros().get(titulo);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }
}
