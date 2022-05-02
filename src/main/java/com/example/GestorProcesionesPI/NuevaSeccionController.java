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
public class NuevaSeccionController implements Serializable {
    
    @Autowired
    ProcesionRepository reppro;
    
    @Autowired
    SeccionRepository repsec;
    
    ArrayList <Seccion> seccionesProcesion = new ArrayList <Seccion>();
    int numSeccionesGuardadas =0;
    
    
    @GetMapping("/crearSecciones")
    public String crearSecciones (@ModelAttribute Seccion seccion, Model model){//@ModelAttribute List<Seccion> seccionesProcesion,
        Procesion procesion= new Procesion();
        Procesion ultimaCreada = reppro.findLastRecordInserted();// OBTENGO EL ID DE LA PROCESION QUE HE CREADO PARA PODER CREAR LAS SECCIONES ASOCIADAS
                
        int numSecciones = ultimaCreada.getNumSecciones();
        
        for(int i=0;i<numSecciones;i++){
            Seccion seccionp = new Seccion();
//          seccionesProcesion[i].setidProcesion(ultimaCreada);
            seccionesProcesion.add(seccionp);  //LE PASO a la vista UNA LISTA DE SECCIONES VACÍAS QUE SE dependen de la procesion Y se rellenan DESDE LA PLANTILLA          
        }
        
        System.out.println(seccionesProcesion.toString());
        model.addAttribute("seccionesProcesion",seccionesProcesion);
        model.addAttribute("procesion",ultimaCreada);
        //long idProcesion = ultimaCreada.getId();
        
      return "nuevaSeccion.html";  
    }
    
     @PostMapping("/crearSecciones")/* guardar seccion y rellenar una seccion vacia de la lista con los datos de la seccion guardada. Mandar lista actualizada de nuevo a la plantilla*/
     public String guardarSecciones (@ModelAttribute Seccion seccion, Model model){
         Procesion ultimaCreada = reppro.findLastRecordInserted(); 
//         seccion.setIdProcesion(ultimaCreada); //LE ASIGNO A LA SECCION EL IDPROCESION DE LA PROCESION DE LA QUE FORMA PARTE
               
//        List <Seccion> secciones = new ArrayList<>();
//        secciones = ultimaCreada.getSecciones();
//        secciones.add(seccion);
//        ultimaCreada.setSecciones(secciones);

          seccion.setIdProcesion(ultimaCreada);
        

        repsec.save(seccion);//GUARDO LA SECCION EN BASE DE DATOS
         
         if(seccionesProcesion.size()>1){
             seccionesProcesion.remove(0);
         }
         
        System.out.println("La lista de procesiones que se está mandando a la plantilla es: "+seccionesProcesion.toString());
        model.addAttribute("seccionesProcesion",seccionesProcesion);
        model.addAttribute("procesion", ultimaCreada);
//         
         return "nuevaSeccion.html";
         
//         System.out.println("Introduciendo la seccion "+seccion.toString());
//         System.out.println("Valor del contador "+numSeccionesGuardadas);
//         
//         seccionesProcesion.set(numSeccionesGuardadas, seccion); //cada vez que se llama el método, se iguala una de las secciones en el array global con los datos que le llegan al método
//         numSeccionesGuardadas=+1;
//         
//         if(numSeccionesGuardadas == seccionesProcesion.size()){ //SOLO CUANDO YA SE HAN RELLENADO LOS DATOS DE TODAS LAS SECCIONES DEL ARRAY, SE VAN PERSISTIENDO TODAS, UNA A UNA
//             for(Seccion s:seccionesProcesion){
//                 s.setIdProcesion(ultimaCreada);
//                 repsec.save(s);
//             }
//         }
         
         
//         
//         
//         System.out.println("datos de la seccion a guardar:"+seccion.toString());         
//        //IMPLEMENTAR MODAL DE GUARDADO CON ÉXITO (ESTE MÉTODO NO REDIRECCIONA Y EL USUARIO DEBE SABER QUE SE HA GUARDADO ANTES DE CONTINUAR)
//        
//         for(Seccion s:seccionesProcesion){
//             if(s.getNombre()==null){
//                 
//                 s.setNombre(seccion.getNombre());
//                 s.setNumTramos(seccion.getNumTramos());
//                 s.setTronoSiNo(seccion.isTronoSiNo());
//                 break;
//             }
//         }
//         
//         
       
         
         
     }
    
}
