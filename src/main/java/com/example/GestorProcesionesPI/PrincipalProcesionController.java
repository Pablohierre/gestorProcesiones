/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Participacion;
import models.Participante;
import models.Procesion;
import models.Seccion;
import models.Tramo;
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
public class PrincipalProcesionController implements Serializable {
    
    @Autowired
    private ProcesionRepository reppro;
    
    @Autowired
    private ParticipacionRepository reppart;
    
    @Autowired
    private SeccionRepository repsec; 
    
    @Autowired
    private TramoRepository reptram;
    
    @GetMapping("/procesion/{id}")           
    public String datosProcesion(Model model, @PathVariable Long id){
        System.out.println("Entra en el metodo datosProcesion");
        Procesion procesion = reppro.getById(id); 
        
        List <Participacion> participaciones =  reppart.findApplicationsByStatus("aprobado", procesion);
        List <Seccion> secciones = repsec.findByidProcesion(procesion);
        List <Tramo> tramos = new ArrayList<>();
        List <Participante> participantesProcesion =new ArrayList<>();
        
        
        
        for(Seccion s: secciones){
           System.out.println("Iteración");
           List <Tramo> tramosSeccion =  reptram.findByIdSeccion(s);
           tramos.addAll(tramosSeccion);
           }
        
        for(Participacion p:participaciones){
            participantesProcesion.add(p.getIdParticipante());

        }

         
        
        
               
        model.addAttribute("procesion", procesion);
        model.addAttribute("participantes", participantesProcesion);
        model.addAttribute("secciones", secciones);
        model.addAttribute("tramos", tramos);
        return "principalProcesion.html";
    }
    
    @GetMapping("/procesion")           
    public String procesion(Model model, @PathVariable Long id){
        
        return "principalProcesion.html";
    }
    
}
