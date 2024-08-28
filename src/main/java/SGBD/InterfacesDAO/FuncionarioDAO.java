package SGBD.InterfacesDAO;

import Services.Funcionarios.Funcionario;

import java.util.List;

public interface FuncionarioDAO {

    List<Funcionario> selectAll();
}
