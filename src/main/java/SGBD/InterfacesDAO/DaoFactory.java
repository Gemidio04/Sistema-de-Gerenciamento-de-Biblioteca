package SGBD.InterfacesDAO;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.JDBC.*;


public class DaoFactory {

    public static LivroDAO createLivroDao(){
        return new LivroDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static EmprestimoLivroDAO createEmprestimoLivroDAO(){
        return new EmprestimoLivroDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static ClienteDAO createClienteDAO(){
        return new ClienteDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static FuncionarioGeralDAO createFuncionarioGeralDAO() {
        return new FuncionarioGeralDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static FuncionarioAdministrativoDAO createFuncionarioAdminstrativoDAO() {
        return new FuncionarioAdministrativoDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static OperacoesBibliotecaDAO createOperacoesBibliotecaDAO(){
        return new OperacoesBibliotecaDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static EstoqueDAO createEstoqueDAO(){
        return new EstoqueDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static AusensiaFuncionarioGeralDAO createAusensiaFuncionarioGeralDAO(){
        return new AusensiaFuncionarioGeralDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static AusenciaFuncionarioAdministrativoDAO createAusensiaFuncionarioAdministrativoDAO(){
        return new AusensiaFuncionarioAdministrativoDaoJDBC(ConexaoBancoDeDados.getConnection());
    }
}
