package Application;

import SGBD.BancoDeDados;

import java.sql.Connection;

public class Principal {

    public static void main(String[] args) {

        Connection connection = BancoDeDados.getConnection();
        BancoDeDados.closeConnection();
    }
}
