
package Test;

import java.util.List;
import pe.edu.pe.implementacion.ClienteDaoImpl;
import pe.edu.pe.interfaces.IClienteDao;
import pe.edu.pe.modelo.Cliente;

/** @author Jhan Arly **/
public class ClienteTest {
    public static void main(String[] args) {
      ClienteTest cl = new ClienteTest();
     // cl.listarClientes();
     // cl.insert();
    // cl.actualizar();
    // cl.eliminar();
    cl.listarClientPorId();
    }
    //metodo para testear la lista de clientes
      public void listarClientes() {
          IClienteDao Cl = new ClienteDaoImpl();
        List<Cliente> listaCliente = Cl.listarClientes();
        if (listaCliente != null && !listaCliente.isEmpty()) {
            for (Cliente cliente : listaCliente) {
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Apellidos: " + cliente.getApellidos());
                System.out.println("Dni: " + cliente.getDni());
                System.out.println("Genero: " + cliente.getGenero());
                System.out.println("Email: " + cliente.getEmail());
            }
        }

    }
      
    public void insert() {
        Cliente c = new Cliente();
        c.setNombre("Juan");
        c.setApellidos("Perz");
        c.setDni("65665556");
        c.setGenero("M");
        c.setEmail("juan@gmail.com");
        IClienteDao clienteDao = new ClienteDaoImpl();
        boolean result = clienteDao.insertar(c);
        if (result) {
            System.out.println("Registro Satisfatorio!!!");
        } else {
            System.out.println("error de registro");
        }

    }
     public void listarClientPorId() {
          IClienteDao Cl = new ClienteDaoImpl();
           Cliente cliente = Cl.buscarPorId(5);
        if (cliente != null) {
            System.out.println("ID: " + cliente.getIdcliente());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellidos: " + cliente.getApellidos());
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Género: " + cliente.getGenero());
        } else {
            System.out.println("Cliente no encontrado con ID: ");
        }
    }

    
    
     public void actualizar() {
        Cliente c = new Cliente();
        c.setNombre("Juan actualizado");
        c.setApellidos("Perez actualizado");
        c.setDni("23232323");
        c.setGenero("M");
        c.setEmail("juanactualizado@gmail.com");
        c.setIdcliente(6);
        IClienteDao clienteDao = new ClienteDaoImpl();
        boolean result = clienteDao.insertar(c);
        if (result) {
            System.out.println("Registro actualizado Satisfatorio!!!");
        } else {
            System.out.println("error de actualizacion");
        }

    }
     
      public void eliminar() {
        Cliente c = new Cliente();
        c.setIdcliente(6);
        IClienteDao clienteDao = new ClienteDaoImpl();
        boolean result = clienteDao.eliminar(c);

        if (result) {
            System.out.println("Registro eliminado Satisfatoriamente!!!");
        } else {
            System.out.println("error de eliminar");
        }

    }

}
