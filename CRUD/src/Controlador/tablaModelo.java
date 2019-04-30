package Controlador;

import javax.swing.table.AbstractTableModel;
import Modelo.consultas;
import java.util.ArrayList;
import Modelo.cliente;
import java.sql.SQLException;

public class tablaModelo extends AbstractTableModel {
    private ArrayList<cliente> lista = new ArrayList<>() ;
    private consultas consulta = null;
    
    public tablaModelo(){
        consulta = new consultas();
        
    }
    
    public void updatemodel() throws SQLException{
        lista = consulta.getAll();

    }
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0: return "rut";
            case 1: return "razon social";
            case 2: return "giro";
            case 3: return "direccion";
            case 4: return "comuna";
            case 5: return "correo";
            case 6: return "telefono";
            default: return "";
        } 
    }
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
      return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        cliente clt = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return clt.getRut();
            case 1: return clt.getRazonSocial();
            case 2: return clt.getGiro();
            case 3: return clt.getDireccion();
            case 4: return clt.getComuna();
            case 5: return clt.getCorreo();
            case 6: return clt.getTelfono();
            default: return "";
        }
    }
    
}
