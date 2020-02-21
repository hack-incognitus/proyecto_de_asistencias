/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_2;
import MODELO_1.m_sancion;
import MODELO_1.m_socio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author raul hacho cutipa
 */
public class socio_DAO {
    private CONEXION mysql = new CONEXION();
    private Connection cn = mysql.conectar();
    private String query = "";

    public DefaultTableModel listar(String dato) {
        DefaultTableModel modelo;

        String[] titulos = {"id Socio" ,"idPadron" ,"apellido Paterno","apellido Materno","nombres","DNI","telefono","genero","fechaNacimiento","foto"};
        Object[] registro = new Object[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        query = "call uspListarSocio(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);
            cst.setString("pDato", dato);
            cst.execute();
            ResultSet rs = cst.getResultSet();

            while (rs.next()) {
                registro[0] = rs.getString("idSocio");
                registro[1] = rs.getString("idPadron");
                registro[2] = rs.getString("apPaterno");
                registro[3] = rs.getString("apMaterno");
                registro[4] = rs.getString("nombres");
                registro[5] = rs.getString("DNI");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("genero");
                registro[8] = rs.getString("fechaNac");
                registro[9] = rs.getString("foto");
               
                modelo.addRow(registro);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de socio con la base de datos listar" + e);

            return null;
        }
    }

    public boolean insertar(m_socio omsocio) {

        query = "call uspInsertarSocio(?,?,?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidPadron", omsocio.getIdPadron());
            cst.setString("papPaterno", omsocio.getApPaterno());
            cst.setString("papMaterno", omsocio.getApMaterno());
            cst.setString("pnombres", omsocio.getNombres());
            cst.setString("pDNI", omsocio.getDNI());
            cst.setString("ptelefono", omsocio.getTelefono());
            cst.setString("pgenero", omsocio.getGenero());
            cst.setString("pfechaNac", omsocio.getFechaNac());
            cst.setString("pfoto", omsocio.getFoto());

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

            JOptionPane.showMessageDialog(null, "error en la clase socio Dao insertar " + e);
            return false;
        } finally {

        }

    }

    public boolean Modificar(m_socio omsocio) {

        query = "call uspModificarSocio(?,?,?,?,?,?,?,?,?,?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

             cst.setString("pidSocio", omsocio.getIdSocio());
            cst.setString("pidPadron", omsocio.getIdPadron());
            cst.setString("papPaterno", omsocio.getApPaterno());
            cst.setString("papMaterno", omsocio.getApMaterno());
            cst.setString("pnombres", omsocio.getNombres());
            cst.setString("pDNI", omsocio.getDNI());
            cst.setString("ptelefono", omsocio.getTelefono());
            cst.setString("pgenero", omsocio.getGenero());
            cst.setString("pfechaNac", omsocio.getFechaNac());
            cst.setString("pfoto", omsocio.getFoto());

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
            JOptionPane.showMessageDialog(null, "error en la clase socio Dao modificar  " + e);

            return false;
        } finally {

        }

    }

    public boolean Eliminar(m_socio omsocio) {

        query = "call uspEliminarSocio(?)";

        try {
            CallableStatement cst = cn.prepareCall(query);

            cst.setString("pidSocio", omsocio.getIdSocio());

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
            JOptionPane.showMessageDialog(null, "error en la clase socio Dao eliminar  " + e);

            return false;
        } finally {

        }

    }
}
