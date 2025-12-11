
package Test;

import java.sql.Connection;
import pe.edu.pe.utilitarios.ConexionBD;

/**
 *
 * @author Jhan Arly
 */
public class Test {

    public static void main(String[] args) {
        Test t = new Test();
        t.testConexion();
    }
    
       public void testConexion() {
           ConexionBD c = new ConexionBD();
        try {
            Connection connection = c.getConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexion satisfatoria");
            } else {
                System.out.println("No se puede establecer conexion");
            }
        } catch (Exception e) {
            System.out.println("Error de conexion" + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
