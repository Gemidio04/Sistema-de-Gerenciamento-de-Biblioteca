package Services.Funcionarios.Assistentes;

import Livros.Livro;
import Services.Exception.ValidacaoException;
import Services.Interfaces.buscaLivros;

public class assistenteBuscaLivros extends Assistente implements buscaLivros {
    private Assistente assistente;

    public assistenteBuscaLivros(){
       super();
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
