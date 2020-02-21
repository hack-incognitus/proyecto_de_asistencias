/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO_1;

/**
 *
 * @author raul hacho cutipa
 */
public class m_padron {
    String id_padron,idTerreno,fechaPadron;

    public m_padron () {
        
    }

    public m_padron(String id_padron, String idTerreno, String fechaPadron) {
        this.id_padron = id_padron;
        this.idTerreno = idTerreno;
        this.fechaPadron = fechaPadron;
    }

    public String getId_padron() {
        return id_padron;
    }

    public void setId_padron(String id_padron) {
        this.id_padron = id_padron;
    }

    public String getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(String idTerreno) {
        this.idTerreno = idTerreno;
    }

    public String getFechaPadron() {
        return fechaPadron;
    }

    public void setFechaPadron(String fechaPadron) {
        this.fechaPadron = fechaPadron;
    }
    
    
}
