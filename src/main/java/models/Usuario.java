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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author hierr
 */

@Entity
@Table(name="usuarios")
@Data
public class Usuario implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String email;
    private String pwd;
    private String tipo; //ADMIN vs USER
    
    @OneToOne(orphanRemoval=true)
    @JoinColumn(name="idparticipante", referencedColumnName="id")
    private Participante idparticipante;
    
    
    @Override
    public String toString() {
        return "usuario: email: "+email+" pwd: "+pwd+" tipo: "+tipo;
    }
}
