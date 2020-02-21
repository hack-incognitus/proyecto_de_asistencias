/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.padron_DAO;
import DAO_2.socio_DAO;
import MODELO_1.m_padron;
import MODELO_1.m_sancion;
import MODELO_1.m_socio;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_socio {

    socio_DAO cSocio = new socio_DAO();
    m_socio omSocio = new m_socio();

    public DefaultTableModel ListarSocio(String Dato) {

        return cSocio.listar(Dato);
    }
    //-----------------------------

    public boolean insertarSocio(String idPadron, String apPaterno, String apMaterno, String nombres,
            String DNI, String telefono, String genero, String fechaNac, String foto) {

        omSocio.setIdPadron(idPadron);
        omSocio.setApPaterno(apPaterno);
        omSocio.setApMaterno(apMaterno);
        omSocio.setNombres(nombres);
        omSocio.setDNI(DNI);
        omSocio.setTelefono(telefono);
        omSocio.setGenero(genero);
        omSocio.setFechaNac(fechaNac);
        omSocio.setFoto(foto);

        return cSocio.insertar(omSocio);
    }

    public boolean modificarSocio(String idSocio, String idPadron, String apPaterno, String apMaterno, 
            String nombres, String DNI, String telefono, String genero, String fechaNac, String foto) {
        
        omSocio.setIdSocio(idSocio);
        omSocio.setIdPadron(idPadron);
        omSocio.setApPaterno(apPaterno);
        omSocio.setApMaterno(apMaterno);
        omSocio.setNombres(nombres);
        omSocio.setDNI(DNI);
        omSocio.setTelefono(telefono);
        omSocio.setGenero(genero);
        omSocio.setFechaNac(fechaNac);
        omSocio.setFoto(foto);

        return cSocio.Modificar(omSocio);

    }

    public boolean eliminarSocio(String id_socio) {

        omSocio.setIdSocio(id_socio);
        return cSocio.Eliminar(omSocio);

    }
}
