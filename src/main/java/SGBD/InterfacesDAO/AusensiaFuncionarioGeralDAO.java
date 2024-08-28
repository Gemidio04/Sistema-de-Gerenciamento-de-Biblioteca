package SGBD.InterfacesDAO;

import Services.Ausencia.AusensiaFuncionarioGeral;

public interface AusensiaFuncionarioGeralDAO {

    void update(AusensiaFuncionarioGeral ausensiaFuncionarioGeral);
    void insert(AusensiaFuncionarioGeral ausensiaFuncionarioGeral);
    void delete(Integer idAusenciaFuncionarioGeral);
    AusensiaFuncionarioGeral selectById(Integer id);
}
