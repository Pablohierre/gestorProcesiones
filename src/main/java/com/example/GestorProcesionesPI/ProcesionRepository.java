/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import models.Procesion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author hierr
 */
public interface ProcesionRepository extends JpaRepository<Procesion, Long> {
    
   @Query("select p from Procesion p where p.id = (select max (p.id) from Procesion p)")
    Procesion findLastRecordInserted();
}
