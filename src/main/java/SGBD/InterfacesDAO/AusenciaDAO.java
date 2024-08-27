package SGBD.InterfacesDAO;

import java.util.List;

public interface AusenciaDAO {

    void delete(Integer id);
    List<String> selectAll();
}
