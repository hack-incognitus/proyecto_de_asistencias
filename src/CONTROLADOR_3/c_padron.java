/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.padron_DAO;
import DAO_2.sancion_DAO;
import MODELO_1.m_padron;
import MODELO_1.m_sancion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_padron {
    padron_DAO cPadron = new padron_DAO();
    m_padron omPadron = new m_padron();

    public DefaultTableModel Listarpadron(String Dato) {

        return cPadron.listar(Dato);
    }
    //-----------------------------

    public boolean insertarPadron( String idTerreno, String fechaPadron) {

        omPadron.setIdTerreno(idTerreno);
        omPadron.setFechaPadron(fechaPadron);
       
        return cPadron.insertar(omPadron);
    }

    public boolean modificarPadron(String id_padron, String idTerreno, String fechaPadron) {
        omPadron.setId_padron(id_padron);
        omPadron.setIdTerreno(idTerreno);
        omPadron.setFechaPadron(fechaPadron);
       
        return cPadron.Modificar(omPadron);

    }

    public boolean eliminarPadron(String id_padron) {

        omPadron.setId_padron(id_padron);
        return cPadron.Eliminar(omPadron);

    }
}
