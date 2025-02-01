package SGBD.InterfacesDAO;

import SGBD.JDBC.FuncionarioGeralDaoJDBC;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.Tipos.FuncionarioAdministrativo;
import Services.Funcionarios.Tipos.FuncionarioGeral;

import java.util.List;

public interface FuncionarioGeralDAO {

    FuncionarioGeralDaoJDBC insert(FuncionarioGeral funcionarioGeral);
    void update(FuncionarioGeral funcionarioGeral);
    void delete(Integer idFuncionarioGeral);
    FuncionarioGeral selectById(Integer id);
    List<FuncionarioGeral> selectAll();
}
