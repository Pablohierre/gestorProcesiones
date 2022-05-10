/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.ArrayList;
import java.util.List;
import models.Participacion;
import models.Procesion;
import models.Seccion;
import models.Tramo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hierr
 */
@Controller
public class PrincipalController {
    
    @Autowired
    ProcesionRepository reppro;
    
    @Autowired
    SeccionRepository repsec;
    
    @Autowired
    TramoRepository reptramo;
    
    @Autowired
    ParticipacionRepository repparticipacion;
    
    @GetMapping("/admin")
    public String principal( Model model){ 
        long millis=System.currentTimeMillis();  
        java.sql.Date fechaDeHoy = new java.sql.Date(millis);
        
        ArrayList<Procesion> procesiones = new ArrayList<>(reppro.findAll()); // SACO TODAS LAS PROCESIONES DE LA BASE DE DATOS
        ArrayList<Procesion> procesionesActivas = new ArrayList<>();
        ArrayList<Procesion> procesionesPasadas = new ArrayList<>();
        
        for(Procesion p:procesiones){
            System.out.println(p.toString());
            if(p.getFecha().after(fechaDeHoy)||p.getFecha().equals(fechaDeHoy)){ // COMPARO LAS FECHAS DE LAS PROCESIONES CON LA FECHA DE HOY Y SEPARO LAS FUTURAS DE LAS PASADAS EN DOS ARRAYLISTS
                procesionesActivas.add(p); 
            }else{
                procesionesPasadas.add(p);
            }
            
        }
        model.addAttribute("activas",procesionesActivas);
        model.addAttribute("pasadas",procesionesPasadas);
        return "Principal.html";
    }
    
    
    @PostMapping("/eliminarProcesion/{idProcesion}")
    public String eliminarProcesion(Model model, @PathVariable Long idProcesion){
        Procesion procesion = reppro.getById(idProcesion);
        List <Seccion> secciones = procesion.getSecciones();
        
        for(Seccion s:secciones){
            List <Tramo> tramos = s.getTramos();//COJO TODOS LOS TRAMOS DE ESA SECCION
                for(Tramo t:tramos){
                    reptramo.delete(t);// BORRO LOS TRAMOS UNO A UNO
                    System.out.println("borrando tramo");
                }
            repsec.delete(s);//BORRO LA SECCION
            System.out.println("borrando seccion");
        }
        
               
//        List <Participacion> participaciones = repparticipacion.findByIdProcesion(procesion);
//        for(Participacion p:participaciones){
//            repparticipacion.delete(p);
//        }
        
        reppro.delete(procesion);
        
        return "redirect:/admin";
    }
}
