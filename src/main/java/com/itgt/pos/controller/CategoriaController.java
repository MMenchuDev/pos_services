package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Categoria;
import com.itgt.pos.service.CategoriaService;
import com.itgt.pos.manager.CategoriaRepository;

@CrossOrigin(originPatterns = {"http://localhost:3000"}) //acceso a datos React
@RestController
@RequestMapping("/api")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;
    
    @Autowired
    CategoriaService service;
    
    
    HashMap<String, Object> mapG = new HashMap<String, Object>();
    List<Categoria> dataG = new ArrayList<>();
    
    @PostMapping("categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
      try {
        Categoria _categoria= categoriaRepository.save(new Categoria(0,categoria.getNombre(), categoria.getDescripcion(), categoria.getCondicion()));
        return new ResponseEntity<>(_categoria, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllCategoria(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<?> items = categoriaRepository.findAll();
        if(items.size() > 0) {
        	map.put("id", 1);
        	map.put("msj", "Elementos encontrados");
        	map.put("data", items);
        	return ResponseEntity.ok(map);
        }else {
        	return ResponseEntity.noContent().build();
        }        
    }
    
    @DeleteMapping("categoria/{id}")
    //@Transactional
    public ResponseEntity<?> deleteCategoria(@PathVariable("id") Long id){
        HashMap<String, Object> map = new HashMap<String, Object>();
    	try{
    		categoriaRepository.deleteById(id);
    		map.put("id", 1);
        	map.put("msj", "Anulado Exisotamente");
        	return ResponseEntity.ok(map);
    	}catch(Exception ex){
    		System.out.println(ex.getMessage());
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @PutMapping("categoria")
    public ResponseEntity<?> updCategoria(@RequestBody Categoria categoria){
    	ResponseEntity<?> response;//= new ResponseEntity<>(null);
    		try {
    			dataG.clear();
    	        Categoria newCategoria = service.updCategoria(categoria);
    	        dataG.add(newCategoria);
        		mapG.put("id", 1);
            	mapG.put("msj", "Actualizado exitosamente");
    			mapG.put("data", dataG);
    			response = ResponseEntity.ok(mapG);
    		}catch(Exception ex) {
    	        response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	return response;
    }
    
}
