package SGBD.InterfacesDAO;

import Services.Estoque.Estoque;

import java.util.List;

public interface EstoqueDAO {

    void insert(Estoque estoque);
    void update(Estoque estoque);
    void delete(Integer id);
    Estoque selectById(Integer id);
    List<Estoque> selectAll();
}
