package Services.Funcionarios;

import Livros.Livro;
import Services.Estoque.EstoqueImplements;
import Services.Exception.ValidacaoException;
import Services.Interfaces.*;

import java.util.HashMap;
import java.util.Map;

public class Bibliotecaria extends Funcionario implements gerenciamentoLivros, emprestimoVendaLivros, buscaLivros, gerenciamentoAdvertencias, estoque {
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;
    private boolean advertencia;
    private int quantidadeAdvertencias;

    private EstoqueImplements estoqueImplements;
    private Map<String, Livro> livros;

    public Bibliotecaria(){
    }
    public Bibliotecaria(EstoqueImplements estoqueImplements) {
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
        advertencia = false;
        quantidadeAdvertencias = 0;
        this.estoqueImplements = estoqueImplements;
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
        estoqueImplements.adicionarLivro(isbn, livro);
    }

    @Override
    public void removerLivro(String isbn) {
        estoqueImplements.removerLivro(isbn);
    }

    @Override
    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        estoqueImplements.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }

    @Override
    public void venderLivro(String isbn) {
        if (checarDisponibilidade(isbn)) {
            estoqueImplements.removerLivro(isbn);
            quantidadeLivrosVendidos++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para venda!");
        }
    }

    @Override
    public void emprestarLivro(String isbn) {
        if (checarDisponibilidade(isbn)) {
            estoqueImplements.removerLivro(isbn);
            quantidadeLivrosEmprestados++;
        } else {
            throw new ValidacaoException("Livro com ISBN " + isbn + " não está disponível para empréstimo!");
        }
    }

    @Override
    public void devolverLivro(String isbn, Livro livro) {
        estoqueImplements.adicionarLivro(isbn, livro);
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

    @Override
    public void adicionarAdvertencia() {
        quantidadeAdvertencias++;
    }

    @Override
    public boolean verificarAdvertencias() {
        return quantidadeAdvertencias > 3;
    }

    public void demitirAssistente(Funcionario funcionario) {
        if (verificarAdvertencias()) {
            System.out.println("Funcionário " + funcionario.getNome() + " demitido!");
        }
    }


}
