package Controlador;

import Modelo.cliente;
import Modelo.consultas;
import Vista.frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class controlador implements ActionListener {

    private final cliente clt;
    private final consultas consulta;
    private final frame marco;
    private ArrayList<cliente> lista = null;
    private tablaModelo tmodelo;

    public controlador(cliente clt, consultas consulta, frame marco) {
        this.clt = clt;
        this.consulta = consulta;
        this.marco = marco;
        this.marco.btnAgregar.addActionListener(this);
        this.marco.btnModificar.addActionListener(this);
        this.marco.btnEliminar.addActionListener(this);
        this.marco.btnBuscar.addActionListener(this);
        this.marco.btnexportar.addActionListener(this);
        this.marco.btnimportar.addActionListener(this);
        this.marco.importee.addActionListener(this);
        this.marco.exportee.addActionListener(this);
    }

    public void iniciar() {
        marco.setTitle("mi crud");
        marco.setLocationRelativeTo(null);
        marco.txt8.setVisible(false);
        tmodelo = new tablaModelo();
        try {
            tmodelo.updatemodel();
        } catch (SQLException ex) {
            Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        marco.tabla.setModel(tmodelo);
        marco.tabla.getTableHeader().setReorderingAllowed(false);
        marco.setResizable(false);

    }

    public void actualizarTabla() {
        try {
            tmodelo.updatemodel();
            tmodelo.fireTableDataChanged();
        } catch (SQLException ex) {
            Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar() {
        marco.txt1.setText(null);
        marco.txt2.setText(null);
        marco.txt3.setText(null);
        marco.txt4.setText(null);
        marco.txt5.setText(null);
        marco.txt6.setText(null);
        marco.txt7.setText(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == marco.btnAgregar) {
            clt.setRut(marco.txt1.getText());
            clt.setRazonSocial(marco.txt2.getText());
            clt.setGiro(marco.txt3.getText());
            clt.setDireccion(marco.txt4.getText());
            clt.setComuna(marco.txt5.getText());
            clt.setCorreo(marco.txt6.getText());
            clt.setTelfono(Integer.parseInt(marco.txt7.getText()));
            if (consulta.agregar(clt)) {
                JOptionPane.showMessageDialog(marco, "registro agregado");
                limpiar();
                actualizarTabla();
            } else {
                System.out.println("falla en agregar");
                limpiar();
            }
        }
        if (e.getSource() == marco.btnModificar) {
            clt.setId(Integer.parseInt(marco.txt8.getText()));
            clt.setRut(marco.txt1.getText());
            clt.setRazonSocial(marco.txt2.getText());
            clt.setGiro(marco.txt3.getText());
            clt.setDireccion(marco.txt4.getText());
            clt.setComuna(marco.txt5.getText());
            clt.setCorreo(marco.txt6.getText());
            clt.setTelfono(Integer.parseInt(marco.txt7.getText()));
            if (consulta.modificar(clt)) {
                JOptionPane.showMessageDialog(marco, "registro modificado");
                limpiar();
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(marco, "ERROR");
                limpiar();
            }
        }
        if (e.getSource() == marco.btnEliminar) {
            clt.setId(Integer.parseInt(marco.txt8.getText()));
            if (consulta.eliminar(clt)) {
                JOptionPane.showMessageDialog(marco, "registro eliminado");
                limpiar();
                actualizarTabla();
            } else {
                System.out.println("falla en eliminar");
                limpiar();
            }

        }
        if (e.getSource() == marco.btnBuscar) {
            clt.setRut(marco.txt1.getText());
            if (consulta.buscar(clt)) {
                System.out.println("cliente buscado correctamente");
                marco.txt8.setText(String.valueOf(clt.getId()));
                marco.txt1.setText(clt.getRut());
                marco.txt2.setText(clt.getRazonSocial());
                marco.txt3.setText(clt.getGiro());
                marco.txt4.setText(clt.getDireccion());
                marco.txt5.setText(clt.getComuna());
                marco.txt6.setText(clt.getCorreo());
                marco.txt7.setText(String.valueOf(clt.getTelfono()));

            } else {
                JOptionPane.showMessageDialog(marco, "registro no encontrado");
            }
        }
        if (e.getSource() == marco.exportee) {
            try {
                consulta.exportarExcel();
            } catch (SQLException ex) {
                System.out.println("falla exportar documento");
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == marco.importee) {
            lista = consulta.importarExcel(marco);
            consulta.agregarAll(lista);
            actualizarTabla();
        }
    }
}
