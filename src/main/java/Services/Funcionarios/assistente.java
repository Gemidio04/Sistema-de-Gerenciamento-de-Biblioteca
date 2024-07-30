package Services.Funcionarios;

import Livros.Livro;
import Services.Estoque.estoque;
import Services.Exception.ValidacaoException;
import Services.Interfaces.*;

import java.util.HashMap;
import java.util.Map;

public class assistente extends funcionario implements gerenciamentoLivros, emprestimoVendaLivros, buscaLivros {
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;

    private estoque estoque;
    private Map<String, Livro> livros;

    public assistente(){
    }
    public assistente(Services.Estoque.estoque estoque) {
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
        this.estoque = estoque;
        this.livros = new HashMap<>();
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

    @Override
    public void adicionarLivro(String isbn, Livro livro) {
        estoque.adicionarLivro(isbn, livro);
    }

    @Override
    public void removerLivro(String isbn) {
        estoque.removerLivro(isbn);
    }

    @Override
    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        estoque.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }

    @Override
    public void venderLivro(String isbn) {
        if (estoque.checarDisponibilidade(isbn)) {
            estoque.removerLivro(isbn);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    @Override
    public void emprestarLivro(String isbn) {
        if (estoque.checarDisponibilidade(isbn)) {
            estoque.removerLivro(isbn);
            quantidadeLivrosEmprestados++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    @Override
    public void devolverLivro(String isbn, Livro livro) {
        estoque.adicionarLivro(isbn, livro);
    }

    @Override
    public Livro buscarLivroIsbn(String isbn) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    @Override
    public Livro buscarLivroAutor(String autor) {
        Livro livro = livros.get(autor);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    @Override
    public Livro buscarLivroTitulo(String titulo) {
        Livro livro = livros.get(titulo);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }
}
