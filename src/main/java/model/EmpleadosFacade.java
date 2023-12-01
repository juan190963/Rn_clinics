/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Reports.Nomina;
import entities.Empleados;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author juand
 */
@Stateless
public class EmpleadosFacade extends AbstractFacade<Empleados> implements EmpleadosFacadeLocal {

    @PersistenceContext(unitName = "BS_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadosFacade() {
        super(Empleados.class);
    }

    @Override
    public List<Nomina> obtenerDatos() {
        List<Nomina> resulList;
        String jpql = "SELECT NEW Reports.Nomina(D.cargo,SUM(D.salario)) FROM Empleados D GROUP BY D.cargo";
        try {
            Query query = em.createQuery(jpql,Nomina.class);
            resulList = query.getResultList();
            return resulList;
        } catch (Exception e) {
        }
        
        return  null;
        
    }
    
    
}
 