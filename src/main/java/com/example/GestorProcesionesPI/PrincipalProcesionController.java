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
import models.Tramo;
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
public class PrincipalProcesionController implements Serializable {
    
    @Autowired
    private ProcesionRepository reppro;
    
    @Autowired
    private ParticipacionRepository reppart;
    
    @Autowired
    private ParticipanteRepository repparticipante;
    
    @Autowired
    private SeccionRepository repsec; 
    
    @Autowired
    private TramoRepository reptram;
    
    @Autowired
    private TronoRepository reptrono;
    
    
    Procesion procesion;
    
    @GetMapping("/procesion/{id}")           
    public String datosProcesion(Model model, @PathVariable Long id){
        System.out.println("Entra en el metodo datosProcesion");
        procesion = reppro.getById(id); 
        
        List <Participacion> participaciones =  reppart.findApplicationsByStatus("aprobado", procesion);
        List <Seccion> secciones = repsec.findByidProcesion(procesion);
        List <Tramo> tramos = new ArrayList<>();
        List <Participante> participantesProcesion =new ArrayList<>();
        List <Trono> tronos = new ArrayList<>();
        
        //Recorro las secciones de la procesion
        for(Seccion s: secciones){
           
           List <Tramo> tramosSeccion =  reptram.findByIdSeccion(s);//Encuentro los tramos de esa seccion
           Trono trono = reptrono.getByIdSeccion(s);//Encuentro el trono perteneciente a esa seccion
            
           if(trono!= null){
            System.out.println(trono.toString());
            trono= calcularDatos(trono);//Calculo las estadisticas de ese trono
            tronos.add(trono);
           }
           
           tramos.addAll(tramosSeccion);
           
           }
        
        for(Participacion p:participaciones){
            participantesProcesion.add(p.getIdParticipante());
        }
        
        System.out.println("Lista de tronos: "+tronos.toString());        
        model.addAttribute("procesion", procesion);
        model.addAttribute("participantes", participantesProcesion);
        model.addAttribute("secciones", secciones);
        model.addAttribute("tramos", tramos);
        model.addAttribute("tronos", tronos);
        return "principalProcesion.html";
    }
    
    @GetMapping("/procesion")           
    public String procesion(Model model, @PathVariable Long id){
        
        return "principalProcesion.html";
    }
    
    public Trono calcularDatos(Trono trono){
        System.out.println("metodo calcularDatos");
        System.out.println(trono.toString());
        int puestosTotales = (trono.getPortadoresVExternos()*2)+ //primero calculo los portadores de los varales externos
                            (trono.getPortadoresVInternos()*(trono.getNumVarales()-2)); //luego calculo el número de varales internos que hay y los multiplico por el numero de portadores que albergan
        
        //busco que puestos de ese trono hay cubiertos encontrando participaciones de tipo portador en la seccion en la que va el trono
        Seccion seccion = trono.getIdSeccion();
        int puestosCubiertos = reppart.findByTipoAndSeccion(seccion, "portador").size();
        System.out.println("puestos cubiertos: "+puestosCubiertos);
        
        int repetidores = reppart.findByTipoAndSeccionWithMultipleParticipacion(seccion, "portador").size();
        System.out.println("repetidores: "+repetidores);
        
        double tasaRepeticion=0.0;
        double talla =0.0;
        int puestosSinAsignar=0;
        double peso =0.0;
        double pesoPorPortador =0.0;
        
        
        if(puestosCubiertos!=0){
         tasaRepeticion= repetidores/puestosCubiertos*100;
        System.out.println("tasa repeticion: "+tasaRepeticion);
        
        
        talla = reppart.findAVGTallaByTipoAndSeccion(seccion, "portador");
        System.out.println("talla media: "+talla);
        
        puestosSinAsignar = reppart.findUnassignedPuestoByTipoAndSeccion(seccion, "portador");
        
        
        peso = reptrono.findPesoById(trono.getId());
        System.out.println("Peso total: "+peso);
         pesoPorPortador= peso/puestosCubiertos;
        System.out.println("p. por portador: "+pesoPorPortador);
        }
        
       // int numRepetidores = repparticipante.findByMultipleParticipacion().size();
        trono.setPuestosTotales(puestosTotales);
        trono.setPuestosCubiertos(puestosCubiertos);
        trono.setTasaRepeticion(tasaRepeticion);
        trono.setTallaMedia(talla);
        trono.setPuestosPorAsignar(puestosSinAsignar);
        trono.setPesoPorCabeza(pesoPorPortador);
        
        System.out.println("finaliza el calculo");
        return trono;
    }
    
}
