package Application;

import SGBD.DAO.*;
import Services.Ausencia.AusensiaFuncionarioAdministrativo;

public class Principal {

    public static void main(String[] args) {

        AusenciaFuncionarioAdministrativoDAO ausenciaFuncionarioAdministrativoDAO = DaoFactory.createAusensiaFuncionarioAdministrativoDao();
        AusensiaFuncionarioAdministrativo ausensiaFuncionarioAdministrativo = ausenciaFuncionarioAdministrativoDAO.selectById(3);

        System.out.println(ausensiaFuncionarioAdministrativo);
    }
}


