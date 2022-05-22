/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Corporacion;
import models.Participacion;
import models.Procesion;
import models.Seccion;
import models.Tramo;
import models.Trono;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hierr
 */
@Controller
public class PrincipalController {
    
    @Autowired
    ProcesionRepository reppro;
    
    @Autowired
    SeccionRepository repsec;
    
    @Autowired
    TramoRepository reptramo;
    
    @Autowired
    ParticipacionRepository repparticipacion;
    
    @Autowired
    TronoRepository reptrono;
    
    @Autowired
    CorporacionRepository repCorp;
    
    
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
        
        Corporacion corporacion =  repCorp.findAll().get(0);
        System.out.println("Datos de la corporacion :"+corporacion.getNombreCorporacion()+" "+corporacion.getColorPrimario()+" "+corporacion.getColorSecundario());
        
        
        model.addAttribute("corporacion", corporacion);
        model.addAttribute("activas",procesionesActivas);
        model.addAttribute("pasadas",procesionesPasadas);
        return "Principal.html";
    }
    
    
    @PostMapping("/eliminarProcesion/{idProcesion}")
    public String eliminarProcesion(Model model, @PathVariable Long idProcesion){
        System.out.println("entra en eliminarProcesion");
        Procesion procesion = reppro.getById(idProcesion);
        List <Seccion> secciones = procesion.getSecciones();
        List <Participacion> participaciones = repparticipacion.findByIdProcesion(procesion);
        
        for(Participacion p:participaciones){
            System.out.println("Borrando participacion id "+p.getId());
            repparticipacion.delete(p);
        }
      
        for(Seccion s:secciones){
            System.out.println("recorriendo secciones");
            Trono trono = reptrono.getByIdSeccion(s);
            if(trono!=null){
                System.out.println("borrando "+trono.getNombre());
                reptrono.delete(trono);
                System.out.println("borrado con éxito");
            }
        }       
        reppro.delete(procesion);
        System.out.println("elimina la procesion");
        
        return "redirect:/admin";
    }
    
    @PostMapping("personalizar")
    public String personalizar(Model model, @ModelAttribute Corporacion corporacion, 
                               @RequestParam("escudo") MultipartFile file){
        
        System.out.println("Datos de la corporacion :" + corporacion.getNombreCorporacion() + " "
                + corporacion.getColorPrimario() + " " + corporacion.getColorSecundario());

        try {

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }

            //recipe.setImage(byteObjects);
            corporacion.setEscudo(byteObjects);

        } catch (IOException e) {
            //todo handle better

            e.printStackTrace();
        }

        repCorp.save(corporacion);

        
        return "redirect:/admin";
    }
    
}
