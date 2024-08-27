package SGBD.InterfacesDAO;

import Services.Ausencia.AusensiaFuncionarioAdministrativo;

public interface AusenciaFuncionarioAdministrativoDAO {

    void update(AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo);
    void insert(AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo);
    AusensiaFuncionarioAdministrativo selectById(Integer id);
}
