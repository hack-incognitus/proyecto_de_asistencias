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
public class m_sancion {
    String idsancion,sancion;
    Double monto;
    String fecha;
    
    public m_sancion(){
        
    }

    public m_sancion(String idsancion, String sancion, Double monto, String fecha) {
        this.idsancion = idsancion;
        this.sancion = sancion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdsancion() {
        return idsancion;
    }

    public void setIdsancion(String idsancion) {
        this.idsancion = idsancion;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
