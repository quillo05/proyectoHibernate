package pojo;
// Generated 13-feb-2016 13:43:44 by Hibernate Tools 4.3.1


import java.time.LocalDate;
import java.util.Date;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private Integer id;
     private Direccion direccion;
     private String nif;
     private String nombre;
     private Date fechaNac;

    public Persona() {
    }
    
    public Persona (String nif) {
        this.nif = nif;
    }

    public Persona(Direccion direccion, String nif, String nombre, LocalDate fechaNac) {
       this.direccion = direccion;
       this.nif = nif;
       this.nombre = nombre;
       this.fechaNac = java.sql.Date.valueOf(fechaNac);
    }
    
    public Persona(String nif, String nombre, LocalDate fechaNac) {
       this.nif = nif;
       this.nombre = nombre;
       this.fechaNac = java.sql.Date.valueOf(fechaNac);
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Direccion getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    public String getNif() {
        return this.nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaNac() {
        return this.fechaNac;
    }
    
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Persona:\nId: "+this.id+"\nNIF: "+this.nif+"\nNombre: "+this.nombre+"\n Fecha de nacimineto: "+this.fechaNac+"\nDirección: "+this.direccion;
    }




}


