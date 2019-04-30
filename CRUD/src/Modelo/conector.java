
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conector {
        
    private Connection conexion = null;
    private final String jbdc = "jdbc:mysql://localhost:3306/clientes";
    private final String usuario = "root";
    private final String clave = "1234";
    
    public Connection getConexion(){
        try{
            conexion = DriverManager.getConnection(jbdc, usuario,clave);
            
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "problemas de conexion con la base de datos ");
           e.printStackTrace();
         return null;
        }
        return conexion;
    }
}
    

