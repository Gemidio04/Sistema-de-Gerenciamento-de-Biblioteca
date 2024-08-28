package SGBD.InterfacesDAO;

import Livros.EmprestimoLivro;

import java.util.List;

public interface EmprestimoLivroDAO {

    void insert(EmprestimoLivro emprestimoLivro);
    void update(EmprestimoLivro emprestimoLivro);
    void delete(Integer idEmprestimoLivro);
    EmprestimoLivro selectById(Integer id);
    List<EmprestimoLivro> selectAll();
}
