package SGBD.InterfacesDAO;

import Services.Funcionarios.Funcionario;

import java.util.List;

public interface FuncionarioDAO {

    void delete(Integer id);
    List<Funcionario> selectAll();
}
