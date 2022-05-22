/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import javax.persistence.Id;
import javax.transaction.Transactional;
import models.Corporacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hierr
 */
public interface CorporacionRepository extends JpaRepository<Corporacion, Id> {
    
  

}
