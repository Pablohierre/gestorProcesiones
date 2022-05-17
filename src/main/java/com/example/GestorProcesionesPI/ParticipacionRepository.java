/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;
import models.Participacion;
import models.Participante;
import models.Procesion;
import models.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hierr
 */
public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {
    
   List <Participacion> findByIdProcesion(Procesion procesion);
   
   List <Participacion> findByIdParticipante(Participante participante);
   
   @Query("Select p from Participacion p where p.modoSolicitud =?1 and p.estado=?2 and p.idProcesion=?3")
   List <Participacion> findOnlineApplicationsByStatus(String modo, String status, Procesion procesion);
   
   @Query("Select p from Participacion p where p.estado=?1 and p.idProcesion=?2")
   List <Participacion> findApplicationsByStatus(String status, Procesion procesion);
   
   @Query("select p from Participacion p where p.idSeccion=?1 and p.tipo=?2")
   List <Participacion> findByTipoAndSeccion(Seccion seccion, String tipo);
   
   @Query("select p from Participacion p where p.idSeccion=?1 and p.tipo=?2 and p.idParticipante in (select pa.idParticipante from Participacion pa  GROUP BY idParticipante HAVING COUNT(idParticipante)>1) ")
    List <Participacion> findByTipoAndSeccionWithMultipleParticipacion(Seccion seccion, String tipo);
    
    @Query("select avg(p.talla) from Participacion p where p.idSeccion=?1 and p.tipo=?2")
    Double findAVGTallaByTipoAndSeccion(Seccion seccion, String tipo);
    
    
    @Query("select count(*) from Participacion p where p.idSeccion=?1 and p.tipo=?2 and p.codPuesto=null ")
    int findUnassignedPuestoByTipoAndSeccion(Seccion seccion, String tipo);
    

   
//    @Query("select p from Participacion p where p.idSeccion=?1 and p.tipo=?2 and COUNT(idParticipante) > 1")
//    List <Participacion> findByTipoAndSeccionWithMultipleParticipacion(Seccion seccion, String tipo);
   
    
}
