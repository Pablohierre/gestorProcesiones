/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import models.Participacion;
import models.Procesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hierr
 */
@Controller
public class ParticipacionController implements Serializable {
    
    
    @Autowired
    ParticipacionRepository Partrep;
    
    
    @PostMapping("/nuevaParticipacion/{idprocesion}")
    public String crearNuevaParticipacion(Model model, @ModelAttribute Procesion idprocesion, Participacion par){
        
       par.setIdProcesion(idprocesion);
       Partrep.save(par);
        
    return "";
    }
}
