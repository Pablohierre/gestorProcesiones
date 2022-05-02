/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */
@Data
@Entity
@Table(name = "tronos")
public class Trono implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "peso", nullable = false)
    private int peso;
    
    @Column(name = "numvarales", nullable = false)
    private int numVarales;
    
    @Column(name = "portadoresvexternos", nullable = false)
    private int PortadoresVExternos; //PORTADORES DE LOS VARALES EXTERNOS
    
    @Column(name = "portadoresvinternos", nullable = false)
    private int PortadoresVInternos; //PORTADORES DE LOS VARALES CON MESA (LOS INTERNOS)
    
    @Column(name = "puestocomienzomesa", nullable = false)
    private int puestoComienzoMesa;//INTRODUCIR OPCION SUBMARINO??????????
    
    @Column(name = "puestofinalmesa", nullable = false)
    private int puestoFinalMesa;//INTRODUCIR OPCION SUBMARINO??????????
    
    
    @OneToOne
    @JoinColumn(name = "idseccion", referencedColumnName="id")
    private Seccion idSeccion; //FK - id de la seccion en la que procesiona el trono
}
