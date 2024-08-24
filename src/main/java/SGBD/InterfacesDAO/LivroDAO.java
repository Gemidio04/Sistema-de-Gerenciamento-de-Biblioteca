package SGBD.InterfacesDAO;

import Livros.Livro;

import java.util.List;

public interface LivroDAO {

    void insert(Livro livro);
    void update(Livro livro);
    void delete(Integer id);
    Livro selectById(String id);
    List<Livro> selectAll();
}
