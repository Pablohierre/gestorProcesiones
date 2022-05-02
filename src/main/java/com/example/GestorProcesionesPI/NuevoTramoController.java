/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Procesion;
import models.Seccion;
import models.Tramo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NuevoTramoController implements Serializable {
    int j =0;//CONTADOR PARA EL METODO DE CARRUSEL
    List <Seccion> secciones = new ArrayList <>();
    int numeroTotalTramos;//para dejar el contador a 0 cuando se hayan persistido tantos tramos como hay creados
    int contador;
    
    @Autowired
    TramoRepository reptram;
    
    @Autowired
    SeccionRepository repsec;
    
     @Autowired
    ProcesionRepository reppro;
    
    @GetMapping("/crearTramos/")//{id}
    public String mostrarTramos(Model model){//, @ModelAttribute Procesion procesion
        System.out.println("Entra en mostrarTramos");
       Procesion procesion = reppro.findLastRecordInserted();
       secciones = procesion.getSecciones();
        System.out.println("secciones: "+secciones.toString());
       Tramo tramoSeccion = new Tramo();
              
       for(Seccion s:secciones){
           List <Tramo> tramosSeccion = new ArrayList <>();
            for (int i = 0; i < s.getNumTramos(); i++) { //CREO TANTOS TRAMOS COMO ESPECIFIQUE EL CAMPO NUMTRAMOS
            
            Tramo tramo = new Tramo();
            System.out.println("Creando nuevo tramo");
            tramo.setIdSeccion(s);
            System.out.println("");
            tramosSeccion.add(tramo);
            
       }
            System.out.println("tramos: "+tramosSeccion);
            s.setTramos(tramosSeccion);
//            System.out.println("La seccion"+s.getNombre()+" tiene "+s.getTramos().size()+" tramos asignados");
        
    }
       model.addAttribute("tramo", tramoSeccion);
       model.addAttribute("seccionesProcesion", secciones);
       model.addAttribute("procesion", procesion);
       return "nuevoTramo.html";
    }
    
  @PostMapping("/crearTramos/{idSeccion}")
    public String guardarTramos (Model model, Tramo tramo, @PathVariable Long idSeccion){
        
        Seccion seccion = repsec.getById(idSeccion);
        tramo.setIdSeccion(seccion); 
        reptram.save(tramo);
        
//        for(Seccion s:secciones){
//            if(s==seccion){
//                s.getTramos().remove(0);
//            }
//        }
        
     
      return "redirect:/crearTramos/";  
    }  
    
}
