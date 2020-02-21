/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_terreno;
import MODELO_1.m_usuario;
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
public class terreno_DAo {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id_terreno", "lote ", "manzana"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarTTerreno(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idTerreno");
                registro[1] = rs.getString("lote");
                registro[2] = rs.getString("manzana");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos" + e);

            return null;
        }
    }

    public boolean insertar(m_terreno omTerreno) {

        query = "call uspInsertarTerreno(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("plote", omTerreno.getLote());
            cst.setString("pmanzana", omTerreno.getManzana());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
          //  int n = cst.executeUpdate();

           // if (n != 0) {
                return true;
           // } else {
              //  return false;
         //   }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase terreno Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_terreno omTerreno) {

        query = "call uspModificarTerreno(?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidTerreno", omTerreno.getIdTerreno());
            cst.setString("plote", omTerreno.getLote());
            cst.setString("pmanzana", omTerreno.getManzana());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
          //  int n = cst.executeUpdate();

         //   if (n != 0) {
                return true;
         //   } else {
            //    return false;
           // }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en la clase terreno Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_terreno omTerreno) {

        query = "call uspEliminarTerreno(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidTerreno", omTerreno.getIdTerreno());

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
            JOptionPane.showMessageDialog(null, "error en la clase terreno Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
}
