package SGBD.DAO;

import Livros.EmprestimoLivro;
import Services.Ausencia.Ausencia;

import java.util.List;

public interface AusenciaDAO {

    void insert(Ausencia ausencia);
    void update(Ausencia ausencia);
    void delete(Integer id);
    List<Ausencia> selectAll();
}
