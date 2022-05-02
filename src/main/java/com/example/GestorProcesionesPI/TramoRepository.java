/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.util.List;
import models.Seccion;
import models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hierr
 */
public interface TramoRepository extends JpaRepository<Tramo, Long> {
    List <Tramo>  findByIdSeccion(Seccion seccion);
}
