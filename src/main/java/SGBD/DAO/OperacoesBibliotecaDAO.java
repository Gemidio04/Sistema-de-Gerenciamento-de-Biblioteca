package SGBD.DAO;

import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

import java.util.List;

public interface OperacoesBibliotecaDAO {

    void insert(OperacoesBiblioteca operacoesBiblioteca);
    void update(OperacoesBiblioteca operacoesBiblioteca);
    void delete(Integer id);
    OperacoesBiblioteca selectById(Integer id);
    List<OperacoesBiblioteca> selectAll();
}
