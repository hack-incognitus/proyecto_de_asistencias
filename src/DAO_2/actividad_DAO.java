/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

////==================================================================================================
package DAO_2;
import MODELO_1.m_actividad;
import MODELO_1.m_sancion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author raul hacho cutipa
 */
public class actividad_DAO {
    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"idActividad" ,"idSocio","nombre","fecha","estado"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarTActividad(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idActividad");
                registro[1] = rs.getString("idSocio");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("fecha");
                registro[4] = rs.getString("estado");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos" + e);

            return null;
        }
    }

    public boolean insertar(m_actividad octividad) {

        query = "call uspInsertarActividad(?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidSocio", octividad.getIdSocio());
            cst.setString("pnombre", octividad.getNombre());
             cst.setString("pfecha", octividad.getFecha());
            cst.setString("pestado", octividad.getEstado());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase actividad Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_actividad omactividad) {

        query = "call uspModificarActividad(?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidActividad", omactividad.getIdActividad());
            cst.setString("pidSocio", omactividad.getIdSocio());
            cst.setString("pnombre", omactividad.getNombre());
            cst.setString("pfecha", omactividad.getFecha());
            cst.setString("pestado", omactividad.getEstado());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase actividad Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_actividad omactividad) {

        query = "call uspEliminarActividad(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidActividad", omactividad.getIdActividad());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));
            }
            //============================
            int n = cst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase sancion Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
    
}
