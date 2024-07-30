package Services.Interfaces;

import Livros.Livro;

public interface buscaLivros {
    Livro buscarLivroIsbn(String isbn);
    Livro buscarLivroAutor(String autor);
    Livro buscarLivroTitulo(String titulo);
}
