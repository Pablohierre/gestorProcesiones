/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.ArrayList;
import models.Procesion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author hierr
 */
@Controller
public class PrincipalController {
    
    @Autowired
    ProcesionRepository reppro;
    
    @GetMapping("/admin")
    public String principal( Model model){ 
        long millis=System.currentTimeMillis();  
        java.sql.Date fechaDeHoy = new java.sql.Date(millis);
        
        ArrayList<Procesion> procesiones = new ArrayList<>(reppro.findAll()); // SACO TODAS LAS PROCESIONES DE LA BASE DE DATOS
        ArrayList<Procesion> procesionesActivas = new ArrayList<>();
        ArrayList<Procesion> procesionesPasadas = new ArrayList<>();
        
        for(Procesion p:procesiones){
            System.out.println(p.toString());
            if(p.getFecha().after(fechaDeHoy)||p.getFecha().equals(fechaDeHoy)){ // COMPARO LAS FECHAS DE LAS PROCESIONES CON LA FECHA DE HOY Y SEPARO LAS FUTURAS DE LAS PASADAS EN DOS ARRAYLISTS
                procesionesActivas.add(p); 
            }else{
                procesionesPasadas.add(p);
            }
            
        }
        model.addAttribute("activas",procesionesActivas);
        model.addAttribute("pasadas",procesionesPasadas);
        return "Principal.html";
    }
}
