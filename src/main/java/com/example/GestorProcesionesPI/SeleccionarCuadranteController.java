/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Corporacion;
import models.Procesion;
import models.Seccion;
import models.Trono;
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
public class SeleccionarCuadranteController implements Serializable {
    
    @Autowired
    SeccionRepository repsec;
    
    @Autowired
    TronoRepository reptrono;
    
    @Autowired
    ProcesionRepository repPro;
    
    @Autowired
    CorporacionRepository repCorp;
    
    @GetMapping("/seleccionarCuadrante/{idProcesion}")
    public String seleccionarCuadrante (Model model, @PathVariable Long idProcesion){
        Procesion procesion = repPro.getById(idProcesion);
        List <Seccion> secciones = repsec.findByidProcesion(procesion);
        List <Trono> tronos = new ArrayList <>();
        
        for(Seccion s:secciones){
            if(reptrono.getByIdSeccion(s)!=null){
           tronos.add(reptrono.getByIdSeccion(s)); 
            }
        }
        System.out.println(tronos.toString());
        model.addAttribute("tronos", tronos);
        Corporacion corporacion =  repCorp.findAll().get(0);
        model.addAttribute("corporacion", corporacion);
        
        return "seleccionarCuadrante.html";
    }
}
