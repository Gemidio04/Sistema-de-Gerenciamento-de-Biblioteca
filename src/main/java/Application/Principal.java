package Application;

import SGBD.bancoDeDados;

import java.sql.Connection;

public class Principal {

    public static void main(String[] args) {

        Connection connection = bancoDeDados.getConnection();
        bancoDeDados.closeConnection();
    }
}
