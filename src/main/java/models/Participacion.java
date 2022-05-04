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
    
    @Column(name = "nombreseccion", nullable = true)
    private String nombreSeccion;

    
     @Column(name = "talla", nullable = true)
   private int talla;
    
    @Column(name = "tunica", nullable = false)
   private String tunica;
    
    @Column(name = "cingulo", nullable = false)
   private String cingulo;
    
    @Column(name = "capirote", nullable = false)
   private String capirote;
    
    @Column(name = "modosolicitud", nullable = false)
    private String modoSolicitud; //ONLINE VS PRESENCIAL
    
     
    @Column(name = "estado", nullable = false)
    private String estado; //PENDIENTE VS APROBADO
    
    public Participacion() {
    }

    @Override
    public String toString() {
        return "Participacion{" + "id=" + id + ", tipo=" + tipo + ", codPuesto=" + codPuesto + ", fecha=" + fecha + ", nombreSeccion=" + nombreSeccion + ", talla=" + talla + ", tunica=" + tunica + ", cingulo=" + cingulo + ", capirote=" + capirote + ", modoSolicitud=" + modoSolicitud + ", estado=" + estado + '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodPuesto() {
        return codPuesto;
    }

    public void setCodPuesto(String codPuesto) {
        this.codPuesto = codPuesto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Procesion getIdProcesion() {
        return idProcesion;
    }

    public void setIdProcesion(Procesion idProcesion) {
        this.idProcesion = idProcesion;
    }

    public Participante getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Participante idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getTunica() {
        return tunica;
    }

    public void setTunica(String tunica) {
        this.tunica = tunica;
    }

    public String getCingulo() {
        return cingulo;
    }

    public void setCingulo(String cingulo) {
        this.cingulo = cingulo;
    }

    public String getCapirote() {
        return capirote;
    }

    public void setCapirote(String capirote) {
        this.capirote = capirote;
    }

    public String getModoSolicitud() {
        return modoSolicitud;
    }

    public void setModoSolicitud(String modoSolicitud) {
        this.modoSolicitud = modoSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
      
      
}
