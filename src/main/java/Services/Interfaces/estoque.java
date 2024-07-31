package Services.Interfaces;

import Livros.Livro;

import java.util.HashMap;
import java.util.Map;

public interface estoque {
    default boolean checarDisponibilidade(String isbn) {
        Map<String, Livro> livros = new HashMap<>();
        Livro livro = livros.get(isbn);
        return livro.getDisponilidade();
    }


}
