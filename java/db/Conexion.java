package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Parámetros de conexión
    private static final String URL      = "jdbc:mysql://localhost:3306/rpg_manager";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "";  // Sin contraseña en XAMPP por defecto

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}