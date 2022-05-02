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
import models.Trono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hierr
 */
@Controller
public class NuevoTronoController implements Serializable {
    List <Seccion> secciones = new ArrayList<>();
    List <Seccion> seccionesConTrono = new ArrayList <>();
    
    @Autowired
    ProcesionRepository reppro;
    
    @Autowired
    SeccionRepository secrep;
    
    @Autowired
    TronoRepository reptrono;
    
    @GetMapping("/crearTronos/{id}")
    public String mostrarTronos(Model model, @PathVariable Long id){
        System.out.println("Entra en el método get");
        Trono trono = new Trono();
       Procesion procesion = reppro.getById(id);  
       secciones = secrep.findByidProcesion(procesion);
        
        if (seccionesConTrono.size() == 0) { //Para evitar que se dupliquen los tronos que se muestran, solo se añaden nuevos tronos cuando la lista está vacía.
            for (Seccion s : secciones) {
                if (s.isTronoSiNo()) {
                    seccionesConTrono.add(s);
                }
            }
        }
        model.addAttribute("trono",trono);
        model.addAttribute("secciones", seccionesConTrono);
        model.addAttribute("procesion", procesion);
        return "nuevoTrono.html";
    }
    
    @PostMapping("/crearTronos/{idSeccion}")
    public String crearTronos(Model model, Trono trono,@PathVariable Long idSeccion){
        Seccion seccion = secrep.getById(idSeccion);
        trono.setIdSeccion(seccion);
        reptrono.save(trono);
        seccionesConTrono.remove(0);
//        Procesion procesion = seccion.getIdProcesion();
        Procesion procesion = reppro.findLastRecordInserted();
        Trono nuevo = new Trono();
        
        model.addAttribute("procesion", procesion);
        model.addAttribute("secciones", seccionesConTrono);
        model.addAttribute("trono", nuevo);
        
        
        return "nuevoTrono.html";
    }
    
}
