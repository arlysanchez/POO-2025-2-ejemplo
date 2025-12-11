package pe.edu.pe.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pe.interfaces.IClienteDao;
import pe.edu.pe.modelo.Cliente;
import pe.edu.pe.utilitarios.ConexionBD;

/**
 * @author Jhan Arly *
 */
public class ClienteDaoImpl implements IClienteDao {

    // Variable global para la conexión a la base de datos
    private Connection cn;

    @Override
    public List<Cliente> listarClientes() {
        // Inicializar variables
        List<Cliente> lista = null;  // Lista para almacenar los resultados
        Cliente cl;                  // Objeto temporal para cada cliente
        PreparedStatement st;        // Para ejecutar la consulta SQL
        ResultSet rs;                // Para almacenar los resultados de la consulta
        String query = null;         // Almacenará la consulta SQL

        try {
            // Consulta SQL para obtener todos los clientes
            query = "SELECT * FROM cliente";
            // Inicializar la lista como ArrayList vacío
            lista = new ArrayList<>();

            // Obtener conexión a la base de datos
            cn = ConexionBD.getConnection();
            // Preparar y ejecutar la consulta
            st = cn.prepareStatement(query);
            rs = st.executeQuery();

            // Procesar cada registro del resultado
            while (rs.next()) {
                cl = new Cliente();
                // Mapear los campos de la base de datos al objeto Cliente
                cl.setIdcliente(rs.getInt("idcliente"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellidos(rs.getString("apellidos"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail(rs.getString("email"));
                cl.setGenero(rs.getString("genero"));

                // Agregar el cliente a la lista
                lista.add(cl);
            }
        } catch (Exception e) {
            // Manejar errores durante la consulta
            System.out.println("Error al listar a las cliente" + e.getMessage());
            try {
                cn.rollback();  // Revertir cambios en caso de error
            } catch (Exception ex) {
                System.out.println("Error al hacer rollback: " + ex.getMessage());
            }
        } finally {
            // Cerrar recursos en el bloque finally para garantizar su ejecución
            if (cn != null) {
                try {
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return lista;  // Devolver la lista de clientes
    }

    @Override
    public boolean insertar(Cliente c) {
        boolean flag = false;

        PreparedStatement st;
        //declaro la variable que va contener la consulta a la base de datos
        String query = null;
        try {
            query = " INSERT INTO cliente (nombre, apellidos, dni, email, genero) VALUES (?, ?, ?, ?, ?);";
            cn = ConexionBD.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, c.getNombre());
            st.setString(2, c.getApellidos());
            st.setString(3, c.getDni());
            st.setString(4, c.getEmail());
            st.setString(5, c.getGenero());
            st.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error al insertar un cliente" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Error, No se pudo agregar el registro");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return flag;
    }

    @Override
    public boolean actualizar(Cliente c) {
        boolean flag = false;

        PreparedStatement st;
        //declaro la variable que va contener la consulta a la base de datos
        String query = null;
        try {
            query = "UPDATE cliente SET nombre = ?, apellidos = ?, dni = ? , genero = ?, email = ? WHERE idcliente = ?;";
            cn = ConexionBD.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, c.getNombre());
            st.setString(2, c.getApellidos());
            st.setString(3, c.getDni());
            st.setString(4, c.getGenero());
            st.setString(5, c.getEmail());
            st.setInt(6, c.getIdcliente());
            st.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error al actualizar un cliente" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Error, No se pudo actualizar el registro");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
        return flag;
    }

    @Override
    public boolean eliminar(Cliente c) {
        boolean flag = false;

        PreparedStatement st;
        //declaro la variable que va contener la consulta a la base de datos
        String query = null;

        try {
            query = "DELETE FROM cliente WHERE idcliente = ?";
            cn = ConexionBD.getConnection();
            st = cn.prepareStatement(query);

            st.setInt(1, c.getIdcliente());
            st.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error al eliminar un cliente" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Error, No se pudo eliminar un cliente por ID");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return flag;
    }

    @Override
    public Cliente buscarPorId(int id) {
        Cliente cl = null;
        PreparedStatement st;
        //resultado que devuelve los datos de la base de datos
        ResultSet rs;
        //declaro la variable que va contener la consulta a la base de datos
        String query = null;
        try {
            //sentencia de sql para traer todas las reservas
            query = "SELECT * FROM cliente WHERE idcliente = ?; ";
            cn = ConexionBD.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {

                cl = new Cliente();
                // Mapear los campos de la base de datos al objeto Cliente
                cl.setNombre(rs.getString("nombre"));
                cl.setApellidos(rs.getString("apellidos"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail(rs.getString("email"));
                cl.setGenero(rs.getString("genero"));
                cl.setIdcliente(rs.getInt("idcliente"));
            }

        } catch (Exception e) {
            System.out.println("Error al buscar una reserva" + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }

            System.out.println("Error, No se pudo buscar la reserva por ID");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception e) {
                }
            }
        }
        return cl;
    }

}
