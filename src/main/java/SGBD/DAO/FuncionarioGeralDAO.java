package SGBD.DAO;

import Services.Funcionarios.Tipos.FuncionarioGeral;

public interface FuncionarioGeralDAO {

    FuncionarioGeral selectById(Integer id);
}
