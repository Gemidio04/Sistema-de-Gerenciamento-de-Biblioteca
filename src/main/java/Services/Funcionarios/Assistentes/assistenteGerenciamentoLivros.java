package Services.Funcionarios.Assistentes;

import Livros.Livro;
import Services.Interfaces.gerenciamentoLivros;

public class assistenteGerenciamentoLivros extends Assistente implements gerenciamentoLivros {
    private Assistente assistente;

    @Override
    public void adicionarLivro(String isbn, Livro livro) {
        assistente.adicionarLivro(isbn, livro);
    }

    @Override
    public void removerLivro(String isbn) {
        assistente.removerLivro(isbn);
    }

    @Override
    public void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao) {
        assistente.atualizarInformacoes(isbn, novoTitulo, novoAutor, novoAnoPublicacao);
    }

}
