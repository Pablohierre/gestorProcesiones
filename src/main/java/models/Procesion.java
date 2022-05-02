/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */
@Entity
@Table(name = "procesiones")

public class Procesion implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    @Column(name = "numsecciones", nullable = false)
    private int numSecciones;
    
//    @OneToMany            ---> ASÍ QUEDARÍA LA RELACIÓN SI SE HACE DE MANERA UNIDIRECCIONAL
//    @JoinColumn(name = "idprocesion")
//    private List <Seccion> secciones;
    
    @OneToMany(mappedBy="idProcesion")
    private List <Seccion> secciones;
    
    @OneToMany(mappedBy="idProcesion")
    private List <Participacion> participaciones;
    

    public Procesion(Long id, String nombre, Date fecha, int numSecciones) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.numSecciones = numSecciones;
    }

   public Procesion(){
       
   }

    @Override
    public String toString() {
        return "Procesion{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", numSecciones=" + numSecciones + '}';
    }

   
   
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumSecciones() {
        return numSecciones;
    }

    public void setNumSecciones(int numSecciones) {
        this.numSecciones = numSecciones;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }
    
    
    
}
