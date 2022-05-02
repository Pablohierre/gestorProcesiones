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

/**
 *
 * @author hierr
 */
public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {
    
   List <Participacion> findByIdProcesion(Procesion procesion);
   
   List <Participacion> findByIdParticipante(Participante participante);
    
}
