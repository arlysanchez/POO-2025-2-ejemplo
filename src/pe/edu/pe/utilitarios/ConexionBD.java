
package pe.edu.pe.utilitarios;
/**
 *
 * @author Jhan Arly
 */

import java.sql.*;

public class ConexionBD {

    public static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Runtime.getRuntime().addShutdownHook(new getClose());
                Class.forName("org.sqlite.JDBC"); 
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\bd\\reserva.db");
                connection.setAutoCommit(true);
                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("PRAGMA foreign_keys = ON;");
                }
                System.out.println("Conexion establecida con SQLite");
            }
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Conexion fallida", e);
        }
    }

    static class getClose extends Thread {

        @Override
        public void run() {
            try {
                Connection conn = ConexionBD.getConnection();
                conn.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}
