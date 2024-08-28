package SGBD.InterfacesDAO;

import Clientes.Cliente;

import java.util.List;

public interface ClienteDAO {

    void insert(Cliente cliente);
    void update(Cliente cliente);
    void delete(Integer idCliente);
    Cliente selectById(Integer id);
    List<Cliente> selectAll();
}
