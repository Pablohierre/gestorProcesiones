/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */

@Entity
@Table(name = "tramos")
@Data
public class Tramo implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
//    @ManyToOne
//    @JoinColumn(name = "id_procesion") //Nombre de la columna de la tabla tramos
//    private Procesion idProcesion; //FK
    
    @ManyToOne(cascade=CascadeType.REMOVE) //muchos tramos pertenecen a la misma sección
    @JoinColumn(name = "idseccion") //Nombre de la columna de la tabla tramos
    private Seccion idSeccion; // FK
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
//    @Column(name = "numparticipantes", nullable = false)
//    private String numParticipantes;
    
    @Column(name = "numfilas", nullable = false)
    private int numFilas;
    
    @Column(name = "tipoparticipantes", nullable = false)
    private String tipoParticipantes;
    
    @Column(name = "tramoscol", nullable = false)
    private String tramoscol; //¿NUMERO DE participantes por fila

    public Tramo() {
    }

    public Tramo(Long id, Procesion idProcesion, Seccion idSeccion, String nombre,  String tipoParticipantes, String tramoscol) { //String numParticipantes
        this.id = id;
//       this.idProcesion = idProcesion;
        this.idSeccion = idSeccion;
        this.nombre = nombre;
//        this.numParticipantes = numParticipantes;
        this.tipoParticipantes = tipoParticipantes;
        this.tramoscol = tramoscol;
    }

   
}
