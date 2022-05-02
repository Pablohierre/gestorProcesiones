/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import models.Participacion;
import models.Participante;
import models.Procesion;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author hierr
 */

@Controller
public class PrincipalUsuarioController implements Serializable {
    @Autowired
    UsuarioRepository repuser;
    
    @Autowired
    ParticipanteRepository reppart;
    
    @Autowired
    ProcesionRepository reppro;
    
    
    @GetMapping("/procesionesActivas/{idUsuario}")
    public String principalUsuarioProcesionesActivas (Model model, @PathVariable Long idUsuario){
        Usuario usuario = repuser.getById(idUsuario);
        Participante participante = usuario.getIdparticipante();// ENCUENTRO EL PARTICIPANTE RELACIONADO A ESE USUARIO
        Participacion participacion = new Participacion();
        
        
        
        long millis=System.currentTimeMillis();  
        java.sql.Date fechaDeHoy = new java.sql.Date(millis);
        
        ArrayList<Procesion> procesiones = new ArrayList<>(reppro.findAll()); // SACO TODAS LAS PROCESIONES DE LA BASE DE DATOS
        ArrayList<Procesion> procesionesActivas = new ArrayList<>();
        
        
        for(Procesion p:procesiones){
            System.out.println(p.toString());
            if(p.getFecha().after(fechaDeHoy)||p.getFecha().equals(fechaDeHoy)){ // COMPARO LAS FECHAS DE LAS PROCESIONES CON LA FECHA DE HOY Y SEPARO LAS FUTURAS DE LAS PASADAS EN DOS ARRAYLISTS
                procesionesActivas.add(p);       
            }
            
        }
        
        model.addAttribute("participacion", participacion);
        model.addAttribute("procesiones", procesionesActivas);
        model.addAttribute("participante", participante);
        
        
        return "principalUsuario.html";
    }
    
    
    
    
    
}
