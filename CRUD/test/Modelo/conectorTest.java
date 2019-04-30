
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class conectorTest {
    
    public conectorTest() {
    }
    
    @Test
    public void testGetConexion() throws SQLException {
        Connection conexion;
        
        conector con = new conector();
       
        conexion = con.getConexion();
        
        assertNotNull(conexion);
        
        conexion.close();
    }

    
}
