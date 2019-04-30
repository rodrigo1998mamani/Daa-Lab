
package Modelo;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

public class consultasTest {
  
    // CLIENTE DE PRUEBA PARA LOS TESTS
    cliente clt = new cliente("prueba","unitaria","junit","prueba","java","correo",30);
    
    public consultasTest() {
    }
    
    @Test
    public void agregarTest(){
    
      consultas sql = new consultas();
      
      
        assertTrue(sql.agregar(clt));
      
    }
    
    @Test
    public void buscarTest(){

      consultas sql = new consultas();
      
        assertTrue(sql.buscar(clt));
      
    }
    
    @Test
    public void modificarTest(){
        
      consultas sql = new consultas();
        
        assertTrue(sql.modificar(clt));
      
    }
    
      @Test
    public void elimiarTest(){

      consultas sql = new consultas();
      
      assertTrue(sql.eliminar(clt));
      
    }
  
    @Test
    public void getAllTest() throws SQLException{
        consultas sql = new consultas();
        
        assertNotNull(sql.getAll());
    }
    @Test
    public void exportarTest() throws SQLException{
        consultas sql=new consultas();
        
        assertTrue(sql.exportarExcel());
    }
}
