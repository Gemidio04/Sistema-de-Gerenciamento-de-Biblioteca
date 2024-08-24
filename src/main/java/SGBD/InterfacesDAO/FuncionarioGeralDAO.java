package SGBD.InterfacesDAO;

import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.util.List;

public interface FuncionarioGeralDAO {

    void insert(FuncionarioGeral funcionarioGeral);
    FuncionarioGeral selectById(Integer id);
//    List<Funcionario> selectAll();
}
