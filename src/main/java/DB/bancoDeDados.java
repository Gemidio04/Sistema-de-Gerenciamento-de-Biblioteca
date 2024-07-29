package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class bancoDeDados {

    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try{
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            }catch (SQLException ex){
                throw new RuntimeException(ex.getMessage());
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
}