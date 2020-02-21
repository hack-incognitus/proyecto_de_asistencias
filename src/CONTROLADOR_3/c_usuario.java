/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR_3;

import MODELO_1.*;
import DAO_2.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul hacho cutipa
 */
public class c_usuario {

    usuario_DAO cUsuarioDao = new usuario_DAO();
    m_usuario omUsuario = new m_usuario();

    public DefaultTableModel ListarUsuario(String Dato) {

        return cUsuarioDao.listar(Dato);
    }
    //-----------------------------

    public boolean insertarUsuario(String nombre_usuario, String tipo_usuario, String correo, String contraseña) {

        omUsuario.setNombre_usuario(nombre_usuario);
        omUsuario.setTipo_usuario(tipo_usuario);
        omUsuario.setCorreo(correo);
        omUsuario.setContraseña(contraseña);

        return cUsuarioDao.insertar(omUsuario);
    }

    public boolean modificarUsuario(String id_usuario, String nombre_usuario, String tipo_usuario, String correo, String contraseña) {
        
        omUsuario.setId_usuario(id_usuario);
        omUsuario.setNombre_usuario(nombre_usuario);
        omUsuario.setTipo_usuario(tipo_usuario);
        omUsuario.setCorreo(correo);
        omUsuario.setContraseña(contraseña);

        return cUsuarioDao.Modificar(omUsuario);

    }

    public boolean eliminarUsuario(String id_usuario) {

        omUsuario.setId_usuario(id_usuario);
        return cUsuarioDao.Eliminar(omUsuario);

    }

    /*public DefaultTableModel Iniciar_sesion(String nombre_usuario, String passwod) {

        return cUsuarioDao.iniciar_sesion(nombre_usuario, passwod);
    }*/
}
