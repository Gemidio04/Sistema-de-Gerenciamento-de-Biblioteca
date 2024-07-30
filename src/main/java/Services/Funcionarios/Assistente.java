package Services.Funcionarios;

import Livros.Livro;
import Services.Interfaces.*;
import java.util.HashMap;
import java.util.Map;


public class Assistente extends Funcionario implements gerenciamentoLivros {
    private Map<String, Livro> livros;

    public Assistente() {
        this.livros = new HashMap<>();
    }

    public void adicionarLivro(String isbn, Livro livro) {
        livros.put(isbn, livro);
    }

    public void removerLivro(String isbn) {
        livros.remove(isbn);
    }

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


}