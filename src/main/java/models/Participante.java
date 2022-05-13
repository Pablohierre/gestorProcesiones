/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */

@Entity
@Table(name = "participantes")
@Data 
public class Participante implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
   private Long id;
    
    @Column(name = "nombre", nullable = false)
   private String nombre;
    
    @Column(name = "apellidos", nullable = false)
   private String apellidos;
    
    @Column(name = "numhermano", nullable = true)
   private int numHermano;
    
    @Column(name = "dni", nullable = false)
   private String dni;
    
    @Column(name = "email", nullable = false)
   private String email;
    
    @Column(name = "telefono", nullable = false)
   private String telefono;
    
    @Column(name = "fechanac", nullable = true)
   private Date fechaNac;
    
    @Column(name = "fechahermano", nullable = true)
   private Date fechaHermano;
       
   
   @OneToMany(mappedBy="idParticipante", cascade=CascadeType.REMOVE)
   private List <Participacion> participaciones;

    public Participante() {
    }

    public Participante(Long id, String nombre, String apellidos, int numHermano, String dni, String email, String telefono, Date fechaNac, Date fechaHermano,  List<Participacion> participaciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numHermano = numHermano;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.fechaHermano = fechaHermano;
        this.participaciones = participaciones;
    }

    


   
   
}
