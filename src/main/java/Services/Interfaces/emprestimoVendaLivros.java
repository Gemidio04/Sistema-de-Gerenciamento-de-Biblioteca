package Services.Interfaces;

import Livros.Livro;

public interface emprestimoVendaLivros {
    void venderLivro(String isbn);
    void emprestarLivro(String isbn);
    void devolverLivro(String isbn, Livro livro);
}
