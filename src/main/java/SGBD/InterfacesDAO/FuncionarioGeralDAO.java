package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioGeral;

public interface FuncionarioGeralDAO {

    void insert(FuncionarioGeral funcionarioGeral);
    void update(FuncionarioGeral funcionarioGeral);
    FuncionarioGeral selectById(Integer id);
}
