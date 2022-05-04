/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GestorProcesionesPI;

import java.io.Serializable;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hierr
 */
@Controller
public class LoginController implements Serializable {
    String retornar="";
    @Autowired
    UsuarioRepository repuser;
    
    @GetMapping("/login")
    public String mostrarLogin(Model model){
       
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        
        return "login.html";
    }
    
    
    @PostMapping("/login")
    public String gestionarLogin (Model model, @ModelAttribute Usuario usuario){
        
        System.out.println("Entra en gestionarLogin");
        Usuario comparador = new Usuario();
        
                
        comparador = repuser.findByEmailAndPwd(usuario.getEmail(), usuario.getPwd());
        System.out.println("Usuario encontrado");
        System.out.println(comparador.toString());
        
       if(comparador.getTipo()=="admin"){
           System.out.println("tipo admin");
           
          retornar= "Principal.html";
           
       } else if (comparador.getTipo()=="user"){
           System.out.println("tipo user");
          
           retornar= "redirect:/procesionesActivas/"+comparador.getId();
       }
        System.out.println("retornando "+ retornar);
       return retornar;
    }
    
}
