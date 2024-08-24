package SGBD.InterfacesDAO;

import Services.Ausencia.Ausencia;
import Services.Ausencia.AusensiaFuncionarioGeral;

import java.util.List;
import java.util.Set;

public interface AusenciaDAO {

    void update(Ausencia ausencia);
    void delete(Integer id);
    List<String> selectAll();
}
