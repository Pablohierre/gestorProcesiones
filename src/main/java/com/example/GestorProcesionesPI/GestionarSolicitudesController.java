/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Participacion;
import models.Procesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author hierr
 */

@Controller
public class GestionarSolicitudesController implements Serializable {
   
    @Autowired
    ParticipacionRepository repparticipacion;
    
    @Autowired
    ProcesionRepository repprocesion;
    
    List <Participacion> listaSolicitudes = new ArrayList<>();
    
   Procesion procesion = new Procesion();
    
    @GetMapping("/gestionarSolicitudesOnline/{idProcesion}/{estado}")
    public String mostrarSolicitudesPorEstado (Model model, @PathVariable Long idProcesion, @PathVariable String estado){
        
        procesion = repprocesion.getById(idProcesion);
        listaSolicitudes = repparticipacion.findOnlineApplicationsByStatus("online", estado, procesion);
        System.out.println(listaSolicitudes.toString());
        model.addAttribute("solicitudes", listaSolicitudes);
        model.addAttribute("procesion", procesion);
        String retornar ="gestionSolicitudes.html";
        System.out.println(estado);
        
//        if(estado=="espera"){
//            System.out.println("entra en el if del espera");
//            return "listaEspera.html";
//        }
        
        switch(estado){
            case "espera":
                System.out.println("Entra en case espera");
                retornar= "listaEspera.html"; 
                break;
                
            case "pendiente":
                System.out.println("Entra en case pendiente");
                retornar ="gestionSolicitudes.html";
                break;
        }
        
        System.out.println("retorna "+retornar);
        return retornar;
       
    }
    
    @PostMapping("/gestionarSolicitudesOnline/rechazar/{idParticipacion}")
    public String rechazarSolicitud(Model model, @PathVariable Long idParticipacion){
        System.out.println("Entra en el metodo rechazar");
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("rechazado");
        repparticipacion.save(participacion);
        listaSolicitudes = repparticipacion.findOnlineApplicationsByStatus("online", "pendiente", procesion);
        model.addAttribute("solicitudes", listaSolicitudes);
        model.addAttribute("procesion", procesion);
        
        return "gestionSolicitudes.html";
    }
    
    @PostMapping("/gestionarSolicitudesOnline/aprobar/{idParticipacion}")
    public String aprobarSolicitud(Model model, @PathVariable Long idParticipacion){
       System.out.println("Entra en el metodo aprobar");
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("aprobado");
        repparticipacion.save(participacion);
        listaSolicitudes = repparticipacion.findOnlineApplicationsByStatus("online", "pendiente", procesion);
        model.addAttribute("solicitudes", listaSolicitudes);
        model.addAttribute("procesion", procesion);
        
        return "gestionSolicitudes.html";
    }
    
    @PostMapping("/gestionarSolicitudesOnline/espera/{idParticipacion}")
    public String ponerSolicitudEnEspera(Model model, @PathVariable Long idParticipacion){
       System.out.println("Entra en el metodo espera");
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("espera");
        repparticipacion.save(participacion);
        listaSolicitudes = repparticipacion.findOnlineApplicationsByStatus("online", "pendiente", procesion);
        model.addAttribute("solicitudes", listaSolicitudes);
        model.addAttribute("procesion", procesion);
        return "gestionSolicitudes.html";
    }
    
}
