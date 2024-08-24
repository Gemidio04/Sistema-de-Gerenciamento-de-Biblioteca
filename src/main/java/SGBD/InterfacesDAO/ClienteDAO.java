package SGBD.InterfacesDAO;

import Clientes.Cliente;
import java.util.List;
import java.util.Set;

public interface ClienteDAO {

    void insert(Cliente cliente);
    void update(Cliente cliente);
    void delete(Integer id);
    Cliente selectById(Integer id);
    List<Cliente> selectAll();
}
