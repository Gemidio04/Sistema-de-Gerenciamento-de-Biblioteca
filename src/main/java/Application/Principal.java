package Application;

import SGBD.BancoDeDados;
import Services.Funcionarios.Funcionario;
import Services.Funcionarios.OperacoesBiblioteca.Gerente;
import Services.Funcionarios.OperacoesBiblioteca.OperacoesBiblioteca;

import java.sql.Connection;

public class Principal {

    public static void main(String[] args) {

//        Connection connection = BancoDeDados.getConnection();
//        BancoDeDados.closeConnection();

        OperacoesBiblioteca operacoesBiblioteca = new OperacoesBiblioteca();
        Gerente gerente = new Gerente();
        gerente.contratarFuncionario(operacoesBiblioteca);
    }
}
