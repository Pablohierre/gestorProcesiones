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
        return "NuevaProcesion.html";
    }
    
    @PostMapping("/nueva")
    public String crearNueva(@ModelAttribute Procesion procesion, Model model){
        System.out.println("Recogiendo datos del formulario");
        repPro.save(procesion); // IMPLEMENTAR MODAL DE "GUARDADO CON EXITO"
        
        //Procesion ultimaCreada = repPro.findLastRecordInserted(procesion);
        //long idProcesion = ultimaCreada.getId();// OBTENGO EL ID DE LA PROCESION QUE HE CREADO PARA PODER CREAR LAS SECCIONES ASOCIADAS
        
        
        return "redirect:/crearSecciones/"; 
    }
}
