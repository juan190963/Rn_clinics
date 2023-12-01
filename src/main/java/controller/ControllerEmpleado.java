/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Reports.Nomina;
import entities.Citas;
import entities.Empleados;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.EmpleadosFacadeLocal;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author juand
 */
@Named(value = "controllerEmpleado")
@ViewScoped
public class ControllerEmpleado implements Serializable {

    Empleados empleados = new Empleados();
    PieChartModel torta;

   
    @EJB
    EmpleadosFacadeLocal efl;

     public PieChartModel getTorta() {
        return torta;
    }

    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }
    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public EmpleadosFacadeLocal getEfl() {
        return efl;
    }

    public void setEfl(EmpleadosFacadeLocal efl) {
        this.efl = efl;
    }
    
    public ControllerEmpleado() {
    }
    
    public List<Empleados> listar(){
        try {
            return this.efl.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    public void insertar(){
        try {
            this.efl.create(empleados);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se inserto correctamente", "MSG_INFO"));
           empleados = new Empleados();
        } catch (Exception e) {
        }
    }
     public void eliminar(Empleados empleados) {
        this.efl.remove(empleados);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino correctamente", "MSG_INFO"));
    }
     public void editar(Empleados empleados) {
        try {
            this.empleados = empleados;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Cita se Actualizo correctamente", "MSG_INFO"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("UpdateCita.xhtml");
            empleados = new Empleados();
        } catch (IOException ex) {
            Logger.getLogger(ControllerEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizar() {
        
        try {
            this.efl.edit(empleados);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Cita se Actualizo correctamente", "MSG_INFO"));
        } catch (Exception e) {
        }
    }
    
    @PostConstruct
    public void init(){
      torta = new PieChartModel();
        try {
           for(Nomina item :this.efl.obtenerDatos()){
           torta.set(item.getCargo(), item.getSalario());
        }
           torta.setTitle("Salario General Del Empleado");
           torta.setLegendPosition("e");
           torta.setFill(true);
           torta.setShowDataLabels(true);
           torta.setDiameter(400);
       } catch (Exception e) {
       }
        
    }
        
    
    
    
    
    
    
    
   public void graficar() {
        torta = new PieChartModel();
        try {
           for(Nomina item :this.efl.obtenerDatos()){
           torta.set(item.getCargo(), item.getSalario());
        }
           torta.setTitle("Salario General Del Empleado");
           torta.setLegendPosition("e");
           torta.setFill(true);
           torta.setShowDataLabels(true);
           torta.setDiameter(400);
       } catch (Exception e) {
       }
        
    }
}

