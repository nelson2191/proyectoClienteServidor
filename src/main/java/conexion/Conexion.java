package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nelsonrivas
 */
public class Conexion {

    //conexion local
    public static Connection conectar() {

        try {
            String myBD = "jdbc:sqlite://localhost/bd_tienda";
            Connection cn = DriverManager.getConnection(myBD);
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexión " + e);
        }
        return null;

    }

}
