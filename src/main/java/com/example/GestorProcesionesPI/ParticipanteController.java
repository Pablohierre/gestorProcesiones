/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.List;
import models.Participacion;
import models.Participante;
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
public class ParticipanteController implements Serializable {
    @Autowired
    ParticipanteRepository partrep;
    
    @Autowired
    ParticipacionRepository participacionrep;
    
    @GetMapping("/participante/{idparticipante}")
    public String mostrarParticipante(Model model, @PathVariable Long idparticipante){
        Participante participante = partrep.getById(idparticipante);
        model.addAttribute("participante", participante);
        
        List <Participacion> participaciones = participacionrep.findByIdParticipante(participante);
        model.addAttribute("participaciones", participaciones);
        
        return "Participante.html";
    }
    
}
