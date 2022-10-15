package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Categoria;
import com.itgt.pos.service.ArticuloService;

@RestController
@RequestMapping("Articulo")
public class ArticuloController {

	@Autowired
	ArticuloService service;
    HashMap<String, Object> mapG = new HashMap<String, Object>();
    List<Articulo> dataG = new ArrayList<>();
    
    @PostMapping("")
    public ResponseEntity<?> updItem(@RequestBody Articulo item){
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
	
}
