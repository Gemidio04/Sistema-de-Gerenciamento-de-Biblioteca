package Services.Funcionarios.OperacoesBiblioteca;

import Livros.Livro;
import Services.Estoque.Estoque;
import Services.Exception.ValidacaoException;
import Services.Funcionarios.Funcionario;

import java.util.HashMap;
import java.util.Map;

public class OperacoesBiblioteca extends Funcionario {

    private Estoque estoque;
    private Map<String, Livro> livros;
    private int quantidadeLivrosVendidos;
    private int quantidadeLivrosEmprestados;

    public OperacoesBiblioteca(){
        super();
        livros = new HashMap<>();
        quantidadeLivrosVendidos = 0;
        quantidadeLivrosEmprestados = 0;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Map<String, Livro> getLivros() {
        return livros;
    }

    public void setLivros(Map<String, Livro> livros) {
        this.livros = livros;
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
        livros.put(isbn, livro);
        estoque.setQuantidade(estoque.getQuantidade() + 1);
    }

    public void removerLivro(String isbn) {
        livros.remove(isbn);
        if(estoque.getQuantidade() > 0)
            estoque.setQuantidade(estoque.getQuantidade() - 1);
        else
            throw new ValidacaoException("Não há mais unidades deste livro!");
    }

    public void atualizarInformacoes
            (String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        Livro livro = livros.get(isbn);
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
        return estoque.getQuantidade() > 0;
    }

    public Livro buscarLivroIsbn(String isbn) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    public Livro buscarLivroAutor(String autor) {
        Livro livro = livros.get(autor);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }

    public Livro buscarLivroTitulo(String titulo) {
        Livro livro = livros.get(titulo);
        if (livro != null) {
            return livro;
        } else {
            throw new ValidacaoException("O livro não existe");
        }
    }
}
