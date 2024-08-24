package SGBD.InterfacesDAO;

import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.util.List;

public interface FuncionarioDAO {

    void update(Funcionario funcionario);
    void delete(Integer id);
    List<Funcionario> selectAll();
}
