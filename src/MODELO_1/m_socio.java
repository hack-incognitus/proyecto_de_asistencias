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
public class m_socio {
    String idSocio ,idPadron ,apPaterno,apMaterno,nombres,DNI,telefono,genero,fechaNac,foto;
    
   public m_socio(){
       
   }

    public m_socio(String idSocio, String idPadron, String apPaterno, String apMaterno, String nombres, String DNI, String telefono, String genero, String fechaNac, String foto) {
        this.idSocio = idSocio;
        this.idPadron = idPadron;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.nombres = nombres;
        this.DNI = DNI;
        this.telefono = telefono;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.foto = foto;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getIdPadron() {
        return idPadron;
    }

    public void setIdPadron(String idPadron) {
        this.idPadron = idPadron;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String text) {
        this.foto = text;
    }
   
   
}
