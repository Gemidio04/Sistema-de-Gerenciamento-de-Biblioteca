package Application;

import SGBD.Connection.ConexaoBancoDeDados;

import java.sql.Connection;

public class Principal {

    public static void main(String[] args) {

        ConexaoBancoDeDados.getConnection();
        System.out.println("A");
        ConexaoBancoDeDados.closeConnection();
    }
}


