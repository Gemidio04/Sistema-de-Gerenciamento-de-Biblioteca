package Services.Estoque;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Interfaces.buscaLivros;
import Services.Interfaces.estoque;
import Services.Interfaces.gerenciamentoLivros;

import java.util.HashMap;
import java.util.Map;

public class EstoqueImplements implements gerenciamentoLivros, buscaLivros, estoque {
    private int quantidade = 150;
    private Map<String, Livro> livros;

    public EstoqueImplements(){
        livros = new HashMap<>();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
        quantidade++;
    }

    @Override
    public void removerLivro(String isbn) {
        livros.remove(isbn);
        if(quantidade > 0)
            quantidade--;
        else
            throw new ValidacaoException("Não há mais unidades deste livro!");
    }

    @Override
    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        Livro livro = livros.get(isbn);
        if (livro != null) {
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAnoPublicacao);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
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
