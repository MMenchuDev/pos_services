package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Persona;
import com.itgt.pos.service.PersonaService;

@RestController
@RequestMapping("Persona")
public class PersonaController {
	@Autowired
	PersonaService service;
    HashMap<String, Object> mapG = new HashMap<String, Object>();
    List<Persona> dataG = new ArrayList<>();
    
    @PostMapping("")
    public ResponseEntity<?> updItem(@RequestBody Persona item){
    	ResponseEntity<?> response;
    		try {
    			dataG.clear();
    	        Persona newItem = service.addItem(item);
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
}