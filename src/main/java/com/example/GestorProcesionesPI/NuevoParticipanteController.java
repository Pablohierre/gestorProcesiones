/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.Participacion;
import models.Participante;
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
public class NuevoParticipanteController implements Serializable {
    
    Participante nuevoParticipante;
    
    Procesion nuevaProcesion;
    
    @Autowired
    SeccionRepository secrep;
    
    @Autowired
    ParticipanteRepository repparticipante;
    
    @Autowired
    ProcesionRepository reppro;
    
    @Autowired
    ParticipacionRepository repparticipacion;
   
    
    @GetMapping("nuevoParticipante/{id}")
    public String nuevoParticipante(Model model, @ModelAttribute Procesion p){
        
        System.out.println("nuevo participante. Procesion "+p.toString());
        
        List<Seccion> secciones = new ArrayList<>();
        secciones = secrep.findByidProcesion(p);
        p.setSecciones(secciones);
        nuevaProcesion = p;
        System.out.println(p.toString());

        Participacion participacion = new Participacion();
        Participante participante = new Participante();
        System.out.println("las secciones de la procesion son: " + p.getSecciones());
        model.addAttribute("participante", participante); //LE PASO UN PARTICIPANTE VACÍO A LA PLANTILLA PARA QUE LO PUEDA RELLENAR EN EL FORMULARIO
        model.addAttribute("procesion", p);//LE PASO A LA PLANTILLA LA PROCESION PARA QUE PUEDA pasarle a  AL METODO DE CREAR PARTICIPACION el id de la procesion
        model.addAttribute("participacion", participacion);
        System.out.println("saliendo del metodo");
        return "nuevoParticipante.html";
    }
    
    @GetMapping("editarParticipacion/{idParticipacion}")
    public String editarParticipante(Model model, @PathVariable Long idParticipacion){
        Participacion participacion = repparticipacion.getById(idParticipacion);
        Participante participante = participacion.getIdParticipante();
        Procesion procesion = participacion.getIdProcesion();
        
        model.addAttribute("participante", participante); 
        model.addAttribute("procesion", procesion);
        model.addAttribute("participacion", participacion);
        return "nuevoParticipante.html";
    }
    
    @PostMapping("nuevoParticipante/{id}")
    public String anadirNuevoParticipante(Model model, Participante p, Participacion par, @ModelAttribute Procesion pro){
        System.out.println("Entra en el metodo anadirNuevoParticipante");
        System.out.println("El valor de la variable global es "+ nuevaProcesion.toString());
        
        if(repparticipante.findFirstByDni(p.getDni())==null){ //Si el participante no existe ya en la BD, lo persisto 
            System.out.println("Participante nuevo");
            repparticipante.save(p);             
               System.out.println("Participante guardado: "+p);             
        }else if (repparticipante.findFirstByDni(p.getDni())!=null){          
            System.out.println("Participante antiguo");
        };   
        

        nuevoParticipante = repparticipante.findFirstByDni(p.getDni());; //le asigno a la variable global el objeto participante que ha entrado en el metodo, para que luego se la pueda pasar al metodo que guarda las participaciones
        
        
        
        
        System.out.println("Valor de la variable global: "+ nuevoParticipante);
        model.addAttribute("participante", nuevoParticipante); //LE PASO UN PARTICIPANTE VACÍO A LA PLANTILLA PARA QUE LO PUEDA RELLENAR EN EL FORMULARIO
        model.addAttribute("procesion", nuevaProcesion);
        model.addAttribute("participacion", par);
        
    return "nuevoParticipante.html";
    }
    
    
    
    @PostMapping("/nuevaParticipacion/presencial/{idParticipante}")//{idprocesion}
    public String crearNuevaParticipacion(Model model,  Participacion par, @PathVariable Long idParticipante){
            System.out.println("Entrando en el metodo nuevaParticipacion");
            System.out.println("El valor de nuevaProcesion es: "+nuevaProcesion.toString());
        
            String nombreSeccion = par.getNombreSeccion(); //Recojo el nombre de seccion indicado en el formulario
            Seccion seccion = secrep.findByIdProcesionAndName(nuevaProcesion, nombreSeccion);
            System.out.println("Seccion encontrada: "+seccion);
            par.setIdSeccion(seccion);

            Participante p = repparticipante.getById(idParticipante);
            System.out.println(p);
            par.setIdParticipante(p); // Le asigno a la participacion el id del participante que acabo de persistir y que me viene de la variable global seteada por el metodo anadirNuevoParticipante
            par.setIdProcesion(nuevaProcesion); // Le asigno a la participacion el id de la procesion, que me viene de la variable global seteada por el metodo anadirNuevoParticipante
            par.setModoSolicitud("presencial");
            par.setEstado("aprobado");
            
            
            System.out.println("Participación: "+par.toString());
            
            repparticipacion.save(par); //persisto la participación
        
    return "redirect:/procesion/"+nuevaProcesion.getId();
    }
    
    
    @PostMapping("nuevaParticipacion/registro-online/{idprocesion}/{idparticipante}")
    public String crearNuevaParticipacionUser(Model model,  Participacion par, @PathVariable Long idprocesion, @PathVariable Long idparticipante){
            System.out.println("Entrando en el metodo nuevaParticipacion");
            
            Procesion procesion = reppro.getById(idprocesion);
            Participante participante = repparticipante.getById(idparticipante);
//            System.out.println("El valor de nuevaProcesion es: "+nuevaProcesion.toString());
            
            String nombreSeccion = par.getNombreSeccion(); //Recojo el nombre de seccion indicado en el formulario
            Seccion seccion = secrep.findByIdProcesionAndName(procesion, nombreSeccion);
            System.out.println("Seccion encontrada: "+seccion);
            par.setIdSeccion(seccion);
        
            par.setIdParticipante(participante); // Le asigno a la participacion el participante que ha entrado por parametro en la URL
            par.setIdProcesion(procesion); // Le asigno a la participacion la procesion que ha entrado por parametro de la URL
            par.setModoSolicitud("online");
            par.setEstado("pendiente");
            par.setTunica("0");
            par.setCingulo("0");
            par.setCapirote("0");
            
            
            
//            System.out.println("Participación: "+par.toString());            
            repparticipacion.save(par); //persisto la participación
        
    return "redirect:/procesionesActivas/"+participante.getId();
    }
        
    
    @PostMapping("buscarParticipante/{id}")
    public String buscarParticipante(Model model, Participante p, Participacion participacion, @ModelAttribute Procesion procesion){
        
                
        System.out.println("DNI: "+ p.getDni());
        nuevoParticipante =repparticipante.findFirstByDni(p.getDni());
        System.out.println("Participante encontrado: "+nuevoParticipante.toString());
        
        model.addAttribute("participante", nuevoParticipante);
        model.addAttribute("participacion", participacion);
        model.addAttribute("procesion", nuevaProcesion);
        System.out.println("las secciones de la procesion son: "+procesion.getSecciones());
        
        return "nuevoParticipante.html";
    }
            
}
