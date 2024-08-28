package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioGeral;

public interface FuncionarioGeralDAO {

    void insert(FuncionarioGeral funcionarioGeral);
    void update(FuncionarioGeral funcionarioGeral);
    void delete(Integer idFuncionarioGeral);
    FuncionarioGeral selectById(Integer id);
}
