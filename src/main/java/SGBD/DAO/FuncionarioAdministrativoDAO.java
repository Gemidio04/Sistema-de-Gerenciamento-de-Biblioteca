package SGBD.DAO;

import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

public interface FuncionarioAdministrativoDAO {
    
    FuncionarioAdministrativo selectById(Integer id);
}
