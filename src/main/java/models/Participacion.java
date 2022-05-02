/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "participaciones")
@Data
public class Participacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "idtipo", nullable = false)
    private String tipo; //PORTADOR, NAZARENO, MONAGUILLO (tabla tipos de puesto) //FK
    
    @Column(name = "codpuesto", nullable = true)
    private String codPuesto;
    
    @Column(name = "fecha", nullable = true)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "idprocesion", nullable = false) //Nombre de la columna de la tabla procesión
    private Procesion idProcesion; //FK
    
    @ManyToOne //MUCHCAS PARTICIPACIONES PUEDEN SER DEL MISMO PARTICIPANTE
    @JoinColumn(name = "idparticipante", nullable = false) //Nombre de la columna de la tabla participacion que lleva la FK
    private Participante idParticipante; //FK
      
    @OneToOne //UNA PARTICIPACION SOLO PUEDE TENER UNA SECCION
    @JoinColumn(name = "idseccion", referencedColumnName = "id") //Nombre de la columna de la tabla secciones
    private Seccion idSeccion; //FK   

    
     @Column(name = "talla", nullable = false)
   private int talla;
    
    @Column(name = "tunica", nullable = false)
   private String tunica;
    
    @Column(name = "cingulo", nullable = false)
   private String cingulo;
    
    @Column(name = "capirote", nullable = false)
   private String capirote;
    
    public Participacion() {
    }
    
       
      public Participacion(Long id_participacion, Procesion idProcesion, Participante idParticipante, Date fecha, Seccion idSeccion, String tipo, String codPuesto) {
        this.id = id;
        this.idProcesion = idProcesion;
        this.idParticipante = idParticipante;
        this.fecha = fecha;
        this.idSeccion = idSeccion;
        this.tipo = tipo;
        this.codPuesto = codPuesto;
    }   
      
      
}
