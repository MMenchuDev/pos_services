package com.itgt.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Categoria;
import com.itgt.pos.manager.CategoriaRepository;

@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;
    
    
    @PostMapping("/categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
      try {
        Categoria _categoria= categoriaRepository.save(new Categoria(0,categoria.getNombre(), categoria.getDescripcion(), categoria.getCondicion()));
        return new ResponseEntity<>(_categoria, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
