//package Services.Funcionarios;
//
//import Livros.Livro;
//import Services.Estoque.Estoque;
//import Services.Exception.ValidacaoException;
//import Services.Interfaces.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Assistente extends Funcionario implements gerenciamentoLivros, emprestimoVendaLivros, buscaLivros {
//    private int quantidadeLivrosVendidos;
//    private int quantidadeLivrosEmprestados;
//
//    private Map<String, Livro> livros;
//    private Estoque estoque;
//    public Assistente() {
//        this.livros = new HashMap<>();
//        quantidadeLivrosVendidos = 0;
//        quantidadeLivrosEmprestados = 0;
//    }
//
//    public void adicionarLivro(String isbn, Livro livro) {
//        livros.put(isbn, livro);
//    }
//
//    public void removerLivro(String isbn) {
//        livros.remove(isbn);
//    }
//
//    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
//        Livro livro = livros.get(isbn);
//        if (livro != null) {
//            livro.setTitulo(novoTitulo);
//            livro.setAutor(novoAutor);
//            livro.setAnoPublicacao(novoAnoPublicacao);
//        } else {
//            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
//        }
//    }
//
//    @Override
//    public void venderLivro(String isbn) {
//        buscarLivro(isbn);
//        removerLivro(isbn);
//        quantidadeLivrosVendidos++;
//    }
//
//    @Override
//    public void emprestarLivro(String isbn) {
//        buscarLivro(isbn);
//        removerLivro(isbn);
//        quantidadeLivrosEmprestados++;
//    }
//
//    @Override
//    public void devolverLivro(String isbn, Livro livro) {
//        adicionarLivro(isbn, livro);
//    }
//
//    @Override
//    public Livro buscarLivro(String isbn) {
//        Livro livro = livros.get(isbn);
//        if (livro.disponibilidade()) {
//            return livro;
//        } else {
//            throw new ValidacaoException("Livro Indisponível!");
//        }
//    }
//}

package Services.Funcionarios;

import Livros.Livro;
import Services.Estoque.Estoque;
import Services.Exception.ValidacaoException;
import Services.Interfaces.*;
import java.util.HashMap;
import java.util.Map;

public class Assistente extends Funcionario implements gerenciamentoLivros, emprestimoVendaLivros, buscaLivros {
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;

    private Estoque estoque;

    public Assistente(){
    }
    public Assistente(Estoque estoque) {
        this.estoque = estoque;
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
    public Livro buscarLivro(String isbn) {
        Livro livro = estoque.buscarLivro(isbn);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não encontrado.");
        }
    }
}
