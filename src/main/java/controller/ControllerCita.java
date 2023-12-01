/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import entities.Citas;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.CitasFacadeLocal;

/**
 *
 * @author juand
 */
@Named(value = "controllerCita")
@ViewScoped
public class ControllerCita implements Serializable {

    Citas citas = new Citas();
    @EJB
    CitasFacadeLocal cfl;

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }

    public CitasFacadeLocal getCfl() {
        return cfl;
    }

    public void setCfl(CitasFacadeLocal cfl) {
        this.cfl = cfl;
    }
    
    public ControllerCita() {
    }
    public  List<Citas> listar(){
        try {
            return this.cfl.findAll();
        } catch (Exception e) {
            return null;
        }
    
    }
    public void insertar(){
    try {
            this.cfl.create(citas);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se inserto correctamente", "MSG_INFO"));
           citas = new Citas();
        } catch (Exception e) {
        }
    }
    public void eliminar (Citas citas){
        this.cfl.remove(citas);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino correctamente", "MSG_INFO"));
    }
    
    public void editar (Citas citas){
         try {
            this.citas = citas;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"La factura se Actualizo correctamente","MSG_INFO"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("updateCita.xhtml");
            citas = new Citas();
        } catch (IOException ex) {
            Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizar(){
    
         try {
                this.cfl.edit(citas);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"La Cita se Actualizo correctamente","MSG_INFO"));
        } catch (Exception e) {
        }
    }
}
