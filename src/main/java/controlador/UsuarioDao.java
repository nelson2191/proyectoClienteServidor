/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author nelsonrivas
 */
public class UsuarioDao {

    Conexion cn = new Conexion();
    Connection conn;

    private static String myBD = "jdbc:sqlite://localhost/bd_tienda";

    {

        // SQL para crear la tabla "Cliente"
        String sql = "CREATE TABLE IF NOT EXISTS tb_usuarios ("
                
                + "nombre TEXT NOT NULL,"
                + "apellidos TEXT NOT NULL,"
                + "idusuario TEXT PRIMARY KEY,"
                + "usuario TEXT NOT NULL,"
                + "contrasena TEXT NOT NULL"
                + ");";

        // Conectar a la base de datos y crear la tabla
        try ( Connection conn = DriverManager.getConnection(myBD);  Statement stmt = conn.createStatement()) {
            // Ejecutar la instrucción SQL
            stmt.execute(sql);
            System.out.println("Tabla 'Usuario' creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }

    }

    public void RegistrarUsuario(Usuario usuario) {
        String insertSql = "Insert Into tb_usuarios(nombre,apellidos,idusuario,usuario,contrasena) VALUES (?,?,?,?,?)";
        try ( Connection conn = DriverManager.getConnection(myBD);  PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setInt(3, usuario.getIdUsuario());
            pstmt.setString(4, usuario.getUsuario());
            pstmt.setString(5, usuario.getPassword());
            pstmt.executeUpdate();
            System.out.println("Registro agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }

    }

    
//metodo para iniciar sesión
    public boolean loginUser(Usuario objeto) {
       
        boolean respuesta = false;

        Connection cn = Conexion.conectar();
        String sql = "select usuario, password from tb_usuarios where usuario= '"+ objeto.getUsuario() +"' and password= '"+ objeto.getPassword() + "'";
        Statement st;
        try {
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                respuesta=true;
                
            }
            
        } catch (SQLException e){
            System.out.println("Error al Iniciar Sesión");
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesión");
        }
        return respuesta;
    }
}
