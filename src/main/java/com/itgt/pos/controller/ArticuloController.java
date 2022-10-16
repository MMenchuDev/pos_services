package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.service.ArticuloService;

@RestController
@RequestMapping("Articulo")
public class ArticuloController {

	@Autowired
	ArticuloService service;
    HashMap<String, Object> mapG = new HashMap<String, Object>();
    List<Articulo> dataG = new ArrayList<>();
    
    @PostMapping("")
    public ResponseEntity<?> addItem(@RequestBody Articulo item){
    	ResponseEntity<?> response;
    		try {
    			dataG.clear();
    	        Articulo newItem = service.addItem(item);
    	        dataG.add(newItem);
        		mapG.put("id", newItem.getId());
            	mapG.put("msj", "agregado exitosamente");
    			mapG.put("data", dataG);
    			response = ResponseEntity.ok(mapG);
    		}catch(Exception ex) {
    	        response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	return response;
    }
    
    @GetMapping("all")
    public ResponseEntity<?> getAllItems(){
    	ResponseEntity<?> response;
    		try {
    			dataG.clear();
    			dataG = service.getAll();
        		mapG.put("id", dataG.size());
            	mapG.put("msj", "Datos obtenidos exitosamente");
    			mapG.put("data", dataG);
    			response = ResponseEntity.ok(mapG);
    		}catch(Exception ex) {
    	        response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	return response;
    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> getArticuloById(@PathVariable("id") Long id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
        	dataG.clear();
            Articulo item = service.getItemById(id);
            dataG.add(item);
            if(dataG.size() > 0) {
            	map.put("id", 1);
            	map.put("msj", "Elemento encontrados");
            	map.put("data", dataG);
            	return ResponseEntity.ok(map);
            }else {
            	return ResponseEntity.noContent().build();
            }         	
        }catch(Exception Ex) {
        	return new ResponseEntity<>(Ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("")
    public ResponseEntity<?> updItem(@RequestBody Articulo data){
    	ResponseEntity<?> response;//= new ResponseEntity<>(null);
    		try {
    			dataG.clear();
    	        Articulo newItem = service.updItem(data);
    	        dataG.add(newItem);
        		mapG.put("id", 1);
            	mapG.put("msj", "Actualizado exitosamente");
    			mapG.put("data", dataG);
    			response = ResponseEntity.ok(mapG);
    		}catch(Exception ex) {
    	        response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	return response;
    }
    
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id){
        HashMap<String, Object> map = new HashMap<String, Object>();
    	try{
    		service.delItem(id);
    		map.put("id", 1);
        	map.put("msj", "Anulado Exisotamente");
        	return ResponseEntity.ok(map);
    	}catch(Exception ex){
    		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
