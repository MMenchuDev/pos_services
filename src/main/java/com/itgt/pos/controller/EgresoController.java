package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Egreso;
import com.itgt.pos.model.Egreso;
import com.itgt.pos.service.EgresoService;

@CrossOrigin
@RestController
@RequestMapping("api/Egreso")
public class EgresoController {
	  @Autowired
	  EgresoService service;
	  HashMap<String, Object> mapG = new HashMap<String, Object>();
	  List<Egreso> dataG = new ArrayList<>();
	  
	  
	  @GetMapping("all")
	  public ResponseEntity<?> getAllItems() {
	    ResponseEntity<?> response;
	    try {
	      dataG.clear();
	      dataG = service.getAll();
	      mapG.put("id", dataG.size());
	      mapG.put("msj", "Datos obtenidos exitosamente");
	      mapG.put("data", dataG);
	      response = ResponseEntity.ok(mapG);
	    } catch (Exception ex) {
	      response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return response;
	  }
	  
	  @GetMapping("id/{id}")
	  public ResponseEntity<?> getEgresoById(@PathVariable("id") Long id) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    try {
	      dataG.clear();
	      Egreso item = service.getItemById(id);
	      dataG.add(item);
	      if (dataG.size() > 0) {
	        map.put("id", 1);
	        map.put("msj", "Elemento encontrados");
	        map.put("data", dataG);
	        return ResponseEntity.ok(map);
	      } else {
	        return ResponseEntity.noContent().build();
	      }
	    } catch (Exception Ex) {
	      return new ResponseEntity<>(Ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }	  
}
