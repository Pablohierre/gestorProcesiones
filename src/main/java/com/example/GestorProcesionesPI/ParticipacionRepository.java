/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;
import models.Participacion;
import models.Participante;
import models.Procesion;
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
    
}
