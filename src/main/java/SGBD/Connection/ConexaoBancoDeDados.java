package SGBD.Connection;

import SGBD.Exception.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConexaoBancoDeDados {

    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try{
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            }catch (SQLException ex){
                throw new DBException(ex.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException ex){
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch (IOException e){
            throw new RuntimeException("Erro nas propriedades de conexão com o Banco de Dados");
        }
    }

    public static void closeStatement(Statement statement) {
       if(statement != null){
           try{
               statement.close();
           } catch (SQLException e) {
               throw new DBException(e.getMessage());
           }
       }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}