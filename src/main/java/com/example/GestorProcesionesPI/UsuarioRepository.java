/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.GestorProcesionesPI;

import models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author hierr
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query("Select u from Usuario u where u.email=?1 and u.pwd=?2")
    Usuario findByEmailAndPwd(String email, String pwd);
    
}
