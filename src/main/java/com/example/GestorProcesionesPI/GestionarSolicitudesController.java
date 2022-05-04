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
    
    @GetMapping("/gestionarSolicitudesOnline/{idProcesion}")
    public String mostrarSolicitudes (Model model, @PathVariable Long idProcesion){
        Procesion procesion = repprocesion.getById(idProcesion);
        
        listaSolicitudes = repparticipacion.findOnlineApplicationsByStatus("online", "pendiente", procesion);
        model.addAttribute("solicitudes", listaSolicitudes);
        
        return "gestionSolicitudes.html";
       
    }
    
    @PutMapping("/gestionarSolicitudesOnline/rechazar/{idParticipacion}")
    public String rechazarSolicitud(Model model, @PathVariable Long idParticipacion){
        
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("rechazado");
        model.addAttribute("solicitudes", listaSolicitudes);
        
        return "gestionSolicitudes.html";
    }
    
    @PutMapping("/gestionarSolicitudesOnline/aprobar/{idParticipacion}")
    public String aprobarSolicitud(Model model, @PathVariable Long idParticipacion){
       
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("aprobado");
        model.addAttribute("solicitudes", listaSolicitudes);
        
        return "gestionSolicitudes.html";
    }
    
    @PutMapping("/gestionarSolicitudesOnline/espera/{idParticipacion}")
    public String ponerSolicitudEnEspera(Model model, @PathVariable Long idParticipacion){
       
        Participacion participacion = repparticipacion.getById(idParticipacion);
        participacion.setEstado("en espera");
        model.addAttribute("solicitudes", listaSolicitudes);
        
        return "gestionSolicitudes.html";
    }
    
}
