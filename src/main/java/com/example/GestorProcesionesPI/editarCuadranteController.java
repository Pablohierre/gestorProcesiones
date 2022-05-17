/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Participacion;
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
public class editarCuadranteController implements Serializable {
    
    @Autowired
    TronoRepository reptrono;
    
    @Autowired
    ParticipacionRepository repparticipacion;
    
    
    @GetMapping("editarCuadrante/{idTrono}")
    public String editarCuadrante (Model model, @PathVariable Long idTrono){
        Trono trono = reptrono.getById(idTrono);
        List <Integer> varales = new ArrayList <Integer>();
        Integer puestos = trono.getPortadoresVExternos();
        List <Integer> puestosVexternos = new ArrayList <Integer>();
        String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J"};
        
        List <String> letrasVarales = new ArrayList <String>();
              
        for(int i=0;i<trono.getNumVarales();i++){
            varales.add(puestos);
            letrasVarales.add(alfabeto[i]);
        }
        
        for (int i=0;i<trono.getPortadoresVExternos();i++ ){
            puestosVexternos.add(i+1);
        }
        
        List <Participacion> portadores = repparticipacion.findByTipoAndSeccion(trono.getIdSeccion(), "portador");      
        
        
        System.out.println(letrasVarales);
        
        model.addAttribute("numVarales", varales);
        model.addAttribute("inicioMesa", trono.getPuestoComienzoMesa());
        model.addAttribute("finalMesa", trono.getPuestoFinalMesa());
        model.addAttribute("puestosVexternos",puestosVexternos);
        model.addAttribute("letrasVarales", letrasVarales);
        model.addAttribute("participaciones", portadores);
        model.addAttribute("trono", trono);       
        
        
        return "editarCuadrante.html";
    }
    
}
