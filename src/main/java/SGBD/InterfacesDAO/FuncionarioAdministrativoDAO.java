package SGBD.InterfacesDAO;

import Services.Funcionarios.Tipos.FuncionarioAdministrativo;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.util.List;

public interface FuncionarioAdministrativoDAO {

    void insert(FuncionarioAdministrativo funcionarioAdministrativo);
    void update(FuncionarioAdministrativo funcionarioAdministrativo);
    void delete(Integer idFuncionarioAdministrativo);
    FuncionarioAdministrativo selectById(Integer id);
    List<FuncionarioAdministrativo> selectAll();
}
