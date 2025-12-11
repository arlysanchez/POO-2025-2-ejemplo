
package pe.edu.pe.interfaces;

/** @author Jhan Arly **/

import java.util.List;
import pe.edu.pe.modelo.Cliente;

public interface IClienteDao {
    //metodo para listar las cliente
    public List<Cliente> listarClientes();
    //metodo para insert cliente
    public boolean insertar(Cliente c);
    //metodo para actualizar cliente
    public boolean actualizar(Cliente c);
    //metodo para eliminar la cliente
    public boolean eliminar(Cliente c);
    
    public Cliente buscarPorId(int id);
}
