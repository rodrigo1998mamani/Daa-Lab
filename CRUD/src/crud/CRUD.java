
/*  CRUD version 1.0.0
*   @AUTOR: RODRIGO MAMANI ESTUDIANTE DE LA CARREA 
*   INGENIERIA EN COMPUTACION DE LA UNIVERSIDAD DE LA SERNA   
*   PARA LA ASIGNATURA DISEÑO Y ANALIS DE ALTGORITMOS
*   
*/
package crud;

import Controlador.controlador;
import Modelo.cliente;
import Modelo.consultas;
import Vista.frame;
import java.sql.SQLException;

public class CRUD {
    public static void main(String[] args) throws SQLException {
        
       cliente clt = new cliente();
       frame marco = new frame();
       consultas consulta = new consultas();
       controlador list = new controlador(clt,consulta,marco);
       list.iniciar();
       marco.setVisible(true);
    }   
}
