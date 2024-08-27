package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

public interface FuncionarioAdministrativoDAO {

    void insert(FuncionarioAdministrativo funcionarioAdministrativo);
    void update(FuncionarioAdministrativo funcionarioAdministrativo);
    FuncionarioAdministrativo selectById(Integer id);
}
