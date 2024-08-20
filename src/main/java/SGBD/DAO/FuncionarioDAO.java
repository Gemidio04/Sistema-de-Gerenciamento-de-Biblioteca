package SGBD.DAO;

import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.util.List;

public interface FuncionarioDAO {

    void insert(Funcionario funcionario);
    void update(Funcionario funcionario);
    void delete(Integer id);
    List<Funcionario> selectAll();
}
