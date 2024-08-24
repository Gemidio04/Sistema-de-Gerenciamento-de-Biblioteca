package SGBD.InterfacesDAO;

import Services.Ausencia.AusensiaFuncionarioAdministrativo;
import Services.Ausencia.AusensiaFuncionarioGeral;

public interface AusenciaFuncionarioAdministrativoDAO {

    void insert(AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo);
    AusensiaFuncionarioAdministrativo selectById(Integer id);
}
