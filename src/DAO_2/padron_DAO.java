/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import MODELO_1.m_padron;
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
public class padron_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id padron", "id terreno ", "fecha padron"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarPadron(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idPadron");
                registro[1] = rs.getString("idTerreno");
                registro[2] = rs.getString("fechaPadron");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos" + e);

            return null;
        }
    }

    public boolean insertar(m_padron ompadron) {

        query = "call uspInsertarPadron(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidTerreno", ompadron.getIdTerreno());
            cst.setString("pfechaPadron", ompadron.getFechaPadron());

            cst.execute();
//================ recuperar ==========0
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString("mensaje_oficial"));

            }
            //============================
           // int n = cst.executeUpdate();

           // if (n != 0) {
                return true;
           // } else {
           //     return false;
           // }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "error en la clase padron Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_padron omPadron) {

        query = "call uspModificarPadron(?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidPadron", omPadron.getId_padron());
            cst.setString("pidTerreno", omPadron.getIdTerreno());
            cst.setString("pfechaPadron", omPadron.getFechaPadron());

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
            JOptionPane.showMessageDialog(null, "error en la clase padron Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_padron ompadron) {

        query = "call uspEliminarPadron(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidPadron", ompadron.getId_padron());

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
            JOptionPane.showMessageDialog(null, "error en la clase padron Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
}
