/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import models.Procesion;
import java.sql.Date;
import java.util.List;
import models.Participacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hierr
 */
@Controller
public class NuevaProcesionController {
    
    @Autowired
    private ProcesionRepository repPro;
    
    @GetMapping("/nueva")
    public String nuevaProcesion(@ModelAttribute Procesion procesion, Model model){
         model.addAttribute("edit", false);
         model.addAttribute("titulo","Crear una Nueva Procesion");
        return "NuevaProcesion.html";
    }
    
    @PostMapping("/nueva")
    public String crearNueva(@ModelAttribute Procesion procesion,  Model model){
        String retornar ="";
        System.out.println("Recogiendo datos del formulario");
        System.out.println(procesion.toString());
        System.out.println("secciones: "+procesion.getSecciones());
        System.out.println("participaciones: "+procesion.getParticipaciones());
        
        Procesion comparador = repPro.getById(procesion.getId());
        
        /* CUANDO SE EDITA LA PROCESIÓN, SOLO LLEGAN AL CONTROLADOR LOS DATOS DEL ID, LA FECHA, EL TITULO Y EL NUMSECCIONES. 
        LA LISTA DE SECCIONES Y PARTICIPACIONES NO LLEGA Y POR ESO HAY QUE VOLVÉRSELA ASIGNAR, YA QUE SI NO, LAS SECCIONES 
        Y LAS PARTICIPACIONES QUE DEPENDEN DE LA PROCESIÓN SE QUEDARIAN SIN SU CALVE FORÁNEA*/
        
        if(comparador!=null){ 
            System.out.println("Procesión ya existente");
            System.out.println("Asignando listas");
            procesion.setParticipaciones(comparador.getParticipaciones());
            procesion.setSecciones(comparador.getSecciones());
            System.out.println("secciones: "+procesion.getSecciones());
            System.out.println("participaciones: "+procesion.getParticipaciones());
            retornar="redirect:/admin/";
            }else{
            retornar="redirect:/crearSecciones/";
        }     
        
        repPro.save(procesion); // IMPLEMENTAR MODAL DE "GUARDADO CON EXITO"        
        return retornar;   
    }
    
    @PostMapping("/editarProcesion/{idProcesion}")
    public String editarProcesion (Model model, @PathVariable Long idProcesion){
        Procesion procesion = repPro.getById(idProcesion);
        
        model.addAttribute("procesion", procesion);
        model.addAttribute("edit", true);
        model.addAttribute("titulo","Editando la procesión "+procesion.getNombre());
        
        return "NuevaProcesion.html";
    }
    

}
