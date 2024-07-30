package Services.Interfaces;

import Livros.Livro;

public interface gerenciamentoLivros {
    void adicionarLivro(String isbn, Livro livro);
    void removerLivro(String isbn);
    void atualizarInformacoes(String isbn, String novoTitulo, String novoAutor, int novoAnoPublicacao);
}
