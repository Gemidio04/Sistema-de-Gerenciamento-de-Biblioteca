package SGBD.DAO;

import SGBD.Connection.ConexaoBancoDeDados;
import SGBD.JDBC.*;


public class DaoFactory {

    public static LivroDAO createLivroDao(){
        return new LivroDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static EmprestimoLivroDAO createEmprestimoLivroDao(){
        return new EmprestimoLivroDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static ClienteDaoJDBC createClienteDao(){
        return new ClienteDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static FuncionarioGeralDAO createFuncionarioGeralDao() {
        return new FuncionarioGeralDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static FuncionarioAdministrativoDAO createFuncionarioAdminstrativoDao() {
        return new FuncionarioAdministrativoDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static OperacoesBibliotecaDAO createOperacoesBibliotecaDao(){
        return new OperacoesBibliotecaDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static EstoqueDAO createEstoqueDao(){
        return new EstoqueDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static AusensiaFuncionarioGeralDAO createAusensiaFuncionarioGeralDao(){
        return new AusensiaFuncionarioGeralDaoJDBC(ConexaoBancoDeDados.getConnection());
    }

    public static AusenciaFuncionarioAdministrativoDAO createAusensiaFuncionarioAdministrativoDao(){
        return new AusensiaFuncionarioAdministrativoDaoJDBC(ConexaoBancoDeDados.getConnection());
    }
}
