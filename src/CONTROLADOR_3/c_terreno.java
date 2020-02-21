/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import DAO_2.*;
import MODELO_1.m_terreno;
import MODELO_1.m_usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_terreno {
    
    terreno_DAo cTerreno = new terreno_DAo();
    m_terreno omTerreno = new m_terreno();
    
    public DefaultTableModel ListarTerreno(String Dato) {
        
        return cTerreno.listar(Dato);
    }
    //-----------------------------

    public boolean insertarterrno(String lote, String manzana) {
        
        omTerreno.setLote(lote);
        omTerreno.setManzana(manzana);
        return cTerreno.insertar(omTerreno);
    }
    
    public boolean modificarTerreno(String idTerreno, String lote, String manzana) {
        omTerreno.setIdTerreno(idTerreno);
        omTerreno.setLote(lote);
        omTerreno.setManzana(manzana);
        return cTerreno.Modificar(omTerreno);
        
    }
    
    public boolean eliminarTerreno(String id_terreno) {
        
        omTerreno.setIdTerreno(id_terreno);
        return cTerreno.Eliminar(omTerreno);
        
    }
}
