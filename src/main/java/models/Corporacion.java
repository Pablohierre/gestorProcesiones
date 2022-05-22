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
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */
@Entity
@Table(name="corporaciones")
@Data
public class Corporacion implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "nombrecorporacion", nullable = false)
    private String nombreCorporacion;
    
    
    @Column(name = "colorprimario", nullable = false)
    private String colorPrimario;
    
    
    @Column(name = "colorsecundario", nullable = false)
    private String colorSecundario;
    
     @Column(name = "nombreimagen", nullable = true)
    private String nombreImagen;
    
    @Lob // ANOTACIÓN PARA BINARIOS POTENCIALMENTE GRANDES
    private Byte[] escudo;
}
