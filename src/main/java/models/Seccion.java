/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */
@Entity
@Table(name = "secciones")

public class Seccion implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "idprocesion") //Nombre de la columna de la tabla Sección que alberga el id de la procesion
    private Procesion idProcesion; //FK
    
    @Column(name = "nombre", nullable = false)
    String nombre;
    
    @Column(name = "numtramos", nullable = false)
    private int numTramos;
    
    @Column(name = "tronosino", nullable = false)
    private boolean tronoSiNo;
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "idSeccion")//nombre del campo de la clase Tramo que mapea este objeto
    private List<Tramo> tramos;
    
    //@OneToOne
   //@JoinColumn(name="idSeccion")
    //private Trono trono;

    public Seccion(Long id, String nombre, Procesion idProcesion, int numTramos, boolean tronoSiNo) {
        this.id = id;
        this.nombre = nombre;
        this.idProcesion = idProcesion;
        this.numTramos = numTramos;
        this.tronoSiNo = tronoSiNo;
    }

    public Seccion() {
    }

    @Override
    public String toString() {
        return "Seccion{" + "id=" + id + ", idProcesion=" + idProcesion + ", nombre=" + nombre + ", numTramos=" + numTramos + ", tronoSiNo=" + tronoSiNo +'}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Procesion getIdProcesion() {
        return idProcesion;
    }

    public void setIdProcesion(Procesion idProcesion) {
        this.idProcesion = idProcesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumTramos() {
        return numTramos;
    }

    public void setNumTramos(int numTramos) {
        this.numTramos = numTramos;
    }

    public boolean isTronoSiNo() {
        return tronoSiNo;
    }

    public void setTronoSiNo(boolean tronoSiNo) {
        this.tronoSiNo = tronoSiNo;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

   
}