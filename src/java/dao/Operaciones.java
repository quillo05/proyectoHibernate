/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojo.*;

/**
 *
 * @author Paco
 */
public class Operaciones {

    public Operaciones () {
        
    }
    
    public Persona mostrarPersona (Persona persona, SessionFactory factory) {
        
        Persona ObjPersona=null;
        
        Session session = factory.openSession();
        Transaction tx = null; 
        
        try { 
            tx = session.beginTransaction();
            String hql = "FROM Persona WHERE nif = '"+persona.getNif()+"'"; 
            Query query = session.createQuery(hql); 
            List personas = query.list();
            
            for (Iterator iterator = personas.iterator(); iterator.hasNext();){ 
                ObjPersona = (Persona) iterator.next();
            }
            
            tx.commit(); 
        } catch (Exception e) { 
            if (tx!=null) {
                tx.rollback();
            }
        }
        
        return ObjPersona;
        
    }
    
    public void insertarPersona (Persona ObjPersona, SessionFactory factory) {
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            
            tx = session.beginTransaction();
            session.save(ObjPersona);
            tx.commit();
            
        } catch (Exception e) { 
            if (tx!=null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        
    }
    
    public boolean borrarPersona (Persona persona, SessionFactory factory) {
        
        Session session = factory.openSession();
        Transaction tx = null; 
        Persona ObjPersona = null;
        boolean borrado = false;
        
            try {
                
            String hql = "FROM Persona WHERE nif = '"+persona.getNif()+"'"; 
            Query query = session.createQuery(hql); 
            List personas = query.list();
            
            for (Iterator iterator = personas.iterator(); iterator.hasNext();){ 
                ObjPersona = (Persona) iterator.next();
            }
            
                System.out.println(ObjPersona);
            
            tx = session.beginTransaction();
            session.delete(ObjPersona);
            tx.commit();
            borrado=true;
            
            } catch (Exception e) { 
            if (tx!=null) {
                tx.rollback();
            }
            } finally {
                session.close();
            }
            
            return borrado;
        
    }
    
    public List <Persona> listarPersonas (SessionFactory factory) {
        
        Session session = factory.openSession();
        String hql = "FROM Persona"; 
        Query query = session.createQuery(hql); 
        List <Persona> personas = query.list();
        return personas;
        
    }
    
    public boolean modificarPersona (Persona persona, SessionFactory factory) {
        
        Session session = factory.openSession();
        Transaction tx = null; 
        Persona ObjPersona = null;
        boolean modificado = false;
        
        try { 
            
            String hql = "FROM Persona WHERE nif = '"+persona.getNif()+"'"; 
            Query query = session.createQuery(hql); 
            List personas = query.list();
            
            Iterator <Persona> iterator = personas.iterator();
            
            while (iterator.hasNext()){ 
                ObjPersona = iterator.next();
            }

            ObjPersona.setNombre(persona.getNombre());
            ObjPersona.setFechaNac(persona.getFechaNac());
            
            Direccion ObjDireccion = new Direccion(persona.getDireccion().getCalle(), persona.getDireccion().getPoblacion(), persona.getDireccion().getProvincia(), persona.getDireccion().getCodigoPostal(), ObjPersona);
            ObjDireccion.setId(ObjPersona.getId());
            ObjPersona.setDireccion(ObjDireccion);

            tx = session.beginTransaction();
            session.update(ObjPersona);
            tx.commit(); 
            modificado=true;
            
        } catch (Exception e) { 
            if (tx!=null) {
                tx.rollback();
            }
        }
        
        return modificado;
        
    }

}
