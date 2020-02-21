/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.actividad_DAO;
import DAO_2.padron_DAO;
import MODELO_1.m_actividad;
import MODELO_1.m_padron;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_actividad {

    actividad_DAO cActividad = new actividad_DAO();
    m_actividad omActividad = new m_actividad();

    public DefaultTableModel ListarActividad(String Dato) {

        return cActividad.listar(Dato);
    }
    //-----------------------------

    public boolean insertarActividad(String idSocio, String nombre, String fecha, String estado) {

        omActividad.setIdSocio(idSocio);
        omActividad.setNombre(nombre);
        omActividad.setFecha(fecha);
        omActividad.setEstado(estado);

        return cActividad.insertar(omActividad);
    }

    public boolean modificarActividad(String idActividad, String idSocio, String nombre, String fecha, String estado) {
       omActividad.setIdActividad(idActividad);
        omActividad.setIdSocio(idSocio);
        omActividad.setNombre(nombre);
        omActividad.setFecha(fecha);
        omActividad.setEstado(estado);

        return cActividad.Modificar(omActividad);

    }

    public boolean eliminarActividad(String id_acti) {

        omActividad.setIdActividad(id_acti);
        return cActividad.Eliminar(omActividad);

    }
}
