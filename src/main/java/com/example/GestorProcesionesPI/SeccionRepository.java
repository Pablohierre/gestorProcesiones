/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;
import models.Procesion;
import models.Seccion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hierr
 */
public interface SeccionRepository extends JpaRepository<Seccion, Long> {
    
    
//    @Query(value="select s from secciones s where s.idprocesion = ?", nativeQuery=true)
//    List <Seccion> seccionesNuevas(Long id);// Devuelve las secciones de la procesion que se acaba de crear
    
    // Seccion findByIdProcesion(Long id);
    
    List<Seccion> findByidProcesion(Procesion idprocesion);

}
