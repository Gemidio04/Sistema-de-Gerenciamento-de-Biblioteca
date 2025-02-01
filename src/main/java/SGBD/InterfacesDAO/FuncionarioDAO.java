package SGBD.InterfacesDAO;

import Services.Funcionarios.Funcionario;

public interface FuncionarioDAO {

    void delete(Integer idFuncionario);
    Funcionario selectById(int id);
}
