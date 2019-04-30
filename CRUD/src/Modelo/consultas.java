package Modelo;

import Vista.frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class consultas extends conector {

    public ArrayList<cliente> lista;

    public boolean agregar(cliente clt) {
        PreparedStatement ps;
        Connection conexion = getConexion();

        String consulta = "INSERT INTO clientes (rut,razon_social,giro,direccion,comuna,correo,telefono) VALUES(?,?,?,?,?,?,?)";
        try {
            ps = conexion.prepareStatement(consulta);

            ps.setString(1, clt.getRut());
            ps.setString(2, clt.getRazonSocial());
            ps.setString(3, clt.getGiro());
            ps.setString(4, clt.getDireccion());
            ps.setString(5, clt.getComuna());
            ps.setString(6, clt.getCorreo());
            ps.setInt(7, clt.getTelfono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("error ");
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean modificar(cliente clt) {
        PreparedStatement ps;
        Connection conexion = getConexion();

        String consulta = "UPDATE clientes SET rut=? ,razon_social =? ,giro =?,direccion=?,comuna=?,correo=?,telefono=? WHERE id=?";
        try {
            ps = conexion.prepareStatement(consulta);

            ps.setString(1, clt.getRut());
            ps.setString(2, clt.getRazonSocial());
            ps.setString(3, clt.getGiro());
            ps.setString(4, clt.getDireccion());
            ps.setString(5, clt.getComuna());
            ps.setString(6, clt.getCorreo());
            ps.setInt(7, clt.getTelfono());
            ps.setInt(8, clt.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean eliminar(cliente clt) {
        PreparedStatement ps;
        Connection conexion = getConexion();
        String consulta = "DELETE FROM clientes WHERE id=?";
        try {
            ps = conexion.prepareStatement(consulta);
            ps.setInt(1, clt.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("error ");
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean buscar(cliente clt) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = getConexion();
        String consulta = "SELECT * FROM clientes WHERE rut=?";
        try {
            ps = conexion.prepareStatement(consulta);

            ps.setString(1, clt.getRut());
            rs = ps.executeQuery();
            if (rs.next()) {
                clt.setId(Integer.parseInt(rs.getString("id")));
                clt.setRazonSocial(rs.getString("razon_social"));
                clt.setGiro(rs.getString("giro"));
                clt.setDireccion(rs.getString("direccion"));
                clt.setComuna(rs.getString("comuna"));
                clt.setCorreo(rs.getString("correo"));
                clt.setTelfono(Integer.parseInt(rs.getString("telefono")));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("error ");
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public ArrayList<cliente> getAll() throws SQLException {
        lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion;
        conexion = getConexion();
        String consulta = " SELECT * FROM clientes ";
        try {
            ps = conexion.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente clt = new cliente();
                clt.setId(Integer.parseInt(rs.getString("id")));
                clt.setRut(rs.getString("rut"));
                clt.setRazonSocial(rs.getString("razon_social"));
                clt.setGiro(rs.getString("giro"));
                clt.setDireccion(rs.getString("direccion"));
                clt.setComuna(rs.getString("comuna"));
                clt.setCorreo(rs.getString("correo"));
                clt.setTelfono(Integer.parseInt(rs.getString("telefono")));
                lista.add(clt);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.close();
        }
        return null;
    }

    public ArrayList<cliente> importarExcel(frame marco) {
        cliente clt;
        lista = new ArrayList<>();
        JFileChooser buscar = new JFileChooser();
        buscar.setFileFilter(new FileNameExtensionFilter("excel", "xlsx"));
        int j = buscar.showOpenDialog(marco);
        File archivo;
        if (j == JFileChooser.APPROVE_OPTION) {
            archivo = buscar.getSelectedFile();
            try {
                FileInputStream excel = new FileInputStream(archivo);
                XSSFWorkbook libro = new XSSFWorkbook(excel);
                XSSFSheet hoja = libro.getSheetAt(0);
                Iterator<Row> itr = hoja.iterator();
                Row fila;
                itr.next(); // primera fina de cabezera
                while (itr.hasNext()) {
                    fila = itr.next();
                    clt = new cliente();
                    clt.setRut(fila.getCell(0).getStringCellValue());
                    clt.setRazonSocial(fila.getCell(1).getStringCellValue());
                    clt.setGiro(fila.getCell(2).getStringCellValue());
                    clt.setDireccion(fila.getCell(3).getStringCellValue());
                    clt.setComuna(fila.getCell(4).getStringCellValue());
                    clt.setCorreo(fila.getCell(5).getStringCellValue());
                    clt.setTelfono((int) fila.getCell(6).getNumericCellValue());
                    lista.add(clt);
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                lista = null;
            } finally {
                return lista;
            }
        } else {
            JOptionPane.showMessageDialog(marco, "opcion de archivo no valida");
            return null;
        }
    }

    public boolean exportarExcel() throws SQLException {
        boolean control = true;
        lista = getAll();
        Workbook libro = new XSSFWorkbook();
        cliente clt ;
        Sheet hoja = libro.createSheet("nuevo");
        for (int i = 0; i < lista.size(); i++) {
            clt = lista.get(i);
            Row fila = hoja.createRow(i);
            fila.createCell(0).setCellValue(clt.getRut());
            fila.createCell(1).setCellValue(clt.getRazonSocial());
            fila.createCell(2).setCellValue(clt.getGiro());
            fila.createCell(3).setCellValue(clt.getDireccion());
            fila.createCell(4).setCellValue(clt.getComuna());
            fila.createCell(5).setCellValue(clt.getCorreo());
            fila.createCell(6).setCellValue(clt.getTelfono());
        }
        try {
            FileOutputStream archivo = new FileOutputStream("miBD.xlsx");
            libro.write(archivo);
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            control = false;
        } catch (IOException ex) {
            ex.printStackTrace();
            control = false;
        } finally {
            return control;
        }
    }

    public void agregarAll(ArrayList<cliente> lista_excel) {
        cliente clt;
        lista = lista_excel;
        Iterator<cliente> itr = lista.iterator();
        while (itr.hasNext()) {
            clt = itr.next();
            agregar(clt);
        }
    }

}
