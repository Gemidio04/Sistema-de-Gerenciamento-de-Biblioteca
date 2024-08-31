package SGBD.InterfacesDAO;

import Livros.Livro;
import Services.Estoque.Estoque;

import java.util.List;

public interface EstoqueDAO {

    void insert(Estoque estoque);
    void update(Estoque estoque);
    void delete(Integer idEstoque);
    Estoque selectById(Integer id);
    Estoque selectByIsbn(String isbn);
    List<Estoque> selectAll();
}
