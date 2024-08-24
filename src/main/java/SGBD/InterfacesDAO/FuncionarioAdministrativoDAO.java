package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

import java.util.List;

public interface FuncionarioAdministrativoDAO {
    
    FuncionarioAdministrativo selectById(Integer id);
    void insert(FuncionarioAdministrativo funcionarioAdministrativo);
}
