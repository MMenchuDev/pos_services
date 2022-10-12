package com.itgt.pos.controller;


//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Usuario;
import com.itgt.pos.manager.UsuarioRepository;

//@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api")
public class UsuarioController {
 
  @Autowired
  UsuarioRepository usuarioRepository;
  
  @PostMapping("/usuarios")
  public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
    try {
      Usuario _usuario = usuarioRepository.save(new Usuario(usuario.getNombre(), usuario.getUsuario(), usuario.getNombre()));
      return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}