package SGBD.InterfacesDAO;

import Services.Ausencia.AusensiaFuncionarioGeral;

public interface AusensiaFuncionarioGeralDAO {

    void insert(AusensiaFuncionarioGeral ausensiaFuncionarioGeral);
    AusensiaFuncionarioGeral selectById(Integer id);
}
