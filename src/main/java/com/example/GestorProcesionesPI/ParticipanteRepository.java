/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;

import models.Participante;
import models.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hierr
 */

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    
    Participante findFirstByDni(String dni);
    
//    @Query("select p from Participante p where p.participaciones.size()>0")
//   List <Participante> findByMultipleParticipacion();
}
