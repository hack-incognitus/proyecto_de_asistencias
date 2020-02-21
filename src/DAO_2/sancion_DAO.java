/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_sancion;
import MODELO_1.m_terreno;
import java.awt.Frame;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class sancion_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;
        
        
        String[] titulos = {"id sancion", "sancion ", "monto", "fecha"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarSancion(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idSancion");
                registro[1] = rs.getString("sancion");
                registro[2] = rs.getDouble("monto");
                registro[3] = rs.getString("fecha");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos" + e);

            return null;
        }
    }

    public boolean insertar(m_sancion omsancion) {

        query = "call uspInsertarSancion(?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("psancion", omsancion.getSancion());
            cst.setDouble("pmonto", omsancion.getMonto());
            cst.setString("pfecha", omsancion.getFecha());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
           //   int n = cst.executeUpdate();

           // if (n != 0) {
                return true;
           // } else {
          //      return false;
          //  }
     
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase sancion Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_sancion omsancion) {

        query = "call uspModificarSancion(?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidSancion", omsancion.getIdsancion());
            cst.setString("psancion", omsancion.getSancion());
            cst.setDouble("pmonto", omsancion.getMonto());
            cst.setString("pfecha", omsancion.getFecha());

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
            JOptionPane.showMessageDialog(null, "error en la clase sancion Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_sancion omsancion) {

        query = "call uspEliminarRegistroSancion(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidSancion", omsancion.getIdsancion());
            
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
