package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioAdministrativo;

public interface FuncionarioAdministrativoDAO {

    void insert(FuncionarioAdministrativo funcionarioAdministrativo);
    void update(FuncionarioAdministrativo funcionarioAdministrativo);
    void delete(Integer idFuncionarioAdministrativo);
    FuncionarioAdministrativo selectById(Integer id);
}
