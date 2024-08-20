package SGBD.DAO;

import Clientes.Cliente;
import java.util.List;

public interface ClienteDAO {

    void insert(Cliente cliente);
    void update(Cliente cliente);
    void delete(Integer id);
    Cliente selectById(Integer id);
    List<Cliente> selectAll();
}
