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
public class m_terreno {
    String idTerreno,lote,manzana;
    public m_terreno(){
        
    }

    public m_terreno(String idTerreno, String lote, String manzana) {
        this.idTerreno = idTerreno;
        this.lote = lote;
        this.manzana = manzana;
    }

    public String getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(String idTerreno) {
        this.idTerreno = idTerreno;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }
    
    
}
