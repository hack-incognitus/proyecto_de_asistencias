/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;

import COMPONENTES.Mensaje_Dialogo1;
import COMPONENTES.Mensaje_Dialogo2;
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
public class usuario_DAO {

    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id_usuario ", "nombre_usuario ", "tipo usuario", "correo", "clave"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarRegistroUsuario(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idUsuario");
                registro[1] = rs.getString("nombreUsuario");
                registro[2] = rs.getString("tipoUsuario");
                registro[3] = rs.getString("correo");
                registro[4] = rs.getString("clave");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion con la base de datos" + e);

            return null;
        }
    }

    public boolean insertar(m_usuario omUsuario) {

        query = "call uspInsertarRegistroUsuario(?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pnombreUsuario", omUsuario.getNombre_usuario());
            cst.setString("ptipoUsuario", omUsuario.getTipo_usuario());
            cst.setString("pcorreo", omUsuario.getCorreo());
            cst.setString("pclave", omUsuario.getContraseña());

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

            JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_usuario omUsuario) {

        query = "call uspModificarRegistroUsuario(?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidUsuario", omUsuario.getId_usuario());
            cst.setString("pnombreUsuario", omUsuario.getNombre_usuario());
            cst.setString("ptipoUsuario", omUsuario.getTipo_usuario());
            cst.setString("pcorreo", omUsuario.getCorreo());
            cst.setString("pclave", omUsuario.getContraseña());

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
             JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao modificar  " + e);
        

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_usuario omUsuario) {

        query = "call uspEliminarRegistroUsuario(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidUsuario", omUsuario.getId_usuario());

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
             JOptionPane.showMessageDialog(null, "error en la clase Usuario Dao eliminar  " + e);
          

            return false;
        } finally {

        }

    }

    // ===========================================================================================00
    /*  public DefaultTableModel iniciar_sesion(String Pnombre_usuario, String Ppasswod) {
        DefaultTableModel modelo;

        String[] titulos = {"acceso"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call usp_iniciar_sesion(?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("Pnombre_usuario", Pnombre_usuario);
            cst.setString("Ppasswod", Ppasswod);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {

                registro[0] = rs.getString("acceso");

                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "error en la clase iniciar sesion  Dao  <listar> " + e);
            return null;
        }
    }*/
}
