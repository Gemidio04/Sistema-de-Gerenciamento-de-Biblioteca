package SGBD.DAO;

import java.sql.Connection;

public class ConexaoDAO {

    protected final Connection connection;

    public ConexaoDAO(Connection connection) {
        this.connection = connection;
    }

}
