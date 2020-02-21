/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.sancion_DAO;
import DAO_2.terreno_DAo;
import MODELO_1.m_sancion;
import MODELO_1.m_terreno;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_sancion {

    sancion_DAO cSancion = new sancion_DAO();
    m_sancion omSancion = new m_sancion();

    public DefaultTableModel ListarSancion(String Dato) {

        return cSancion.listar(Dato);
    }
    //-----------------------------

    public boolean insertarSancion(String sancion, Double monto, String fecha) {

        omSancion.setSancion(sancion);
        omSancion.setMonto(monto);
        omSancion.setFecha(fecha);
        return cSancion.insertar(omSancion);
    }

    public boolean modificarSancion(String idsancion, String sancion, Double monto, String fecha) {
        omSancion.setIdsancion(idsancion);
        omSancion.setSancion(sancion);
        omSancion.setMonto(monto);
        omSancion.setFecha(fecha);
        return cSancion.Modificar(omSancion);

    }

    public boolean eliminarSancion(String id_sancion) {

        omSancion.setIdsancion(id_sancion);
        return cSancion.Eliminar(omSancion);

    }
}
