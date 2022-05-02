/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import models.Trono;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hierr
 */
public interface TronoRepository extends JpaRepository<Trono, Long> {
    
}
