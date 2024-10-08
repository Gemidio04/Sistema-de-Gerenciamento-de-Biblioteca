package SGBD.InterfacesDAO;

import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

import java.util.List;

public interface OperacoesBibliotecaDAO {

    void insert(OperacoesBiblioteca operacoesBiblioteca);
    void update(OperacoesBiblioteca operacoesBiblioteca);
    void delete(Integer idOperacoesBiblioteca);
    OperacoesBiblioteca selectById(Integer id);
    List<OperacoesBiblioteca> selectAll();
}
