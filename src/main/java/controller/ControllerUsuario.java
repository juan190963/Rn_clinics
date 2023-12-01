/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import entities.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.UsuariosFacadeLocal;

/**
 *
 * @author juand
 */
@Named(value = "controllerUsuario")
@SessionScoped
public class ControllerUsuario implements Serializable {

    Usuarios usuarios = new Usuarios();
    @EJB
    UsuariosFacadeLocal ufl;

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuariosFacadeLocal ufl) {
        this.ufl = ufl;
    }
    
    public ControllerUsuario() {
    }
   public String validarUsuario() {
        String redir = "";
        try {
            usuarios = ufl.IniciarSesion(usuarios);
            if (usuarios != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarios.getCorreo());
                
                //Validacion de rolles
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Cargo","usuario");
                
                redir = "inicio.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(redir);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario o Contraseña equivocados", "MSG_ERROR"));
            }
        } catch (Exception e) {
        }
        return redir;
    }
}
