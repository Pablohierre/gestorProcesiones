/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;
import models.Seccion;
import models.Trono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hierr
 */
public interface TronoRepository extends JpaRepository<Trono, Long> {
    
    Trono getByIdSeccion(Seccion seccion);
    
    @Query("Select t.peso from Trono t where t.id=?1")
    int findPesoById(Long idTrono);
    
}
