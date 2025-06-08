package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Datos para la conexión
 * Proporciona un método estático para obtener una conexión activa.
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://192.168.1.136:5432/tareas";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "usuario1";

    /**
     * Obtiene una conexión a la base de datos PostgreSQL especificada.
     *
     * @return Objeto {@link Connection} activo para realizar operaciones en la base de datos.
     * @throws SQLException Si ocurre un error al intentar conectar con la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
