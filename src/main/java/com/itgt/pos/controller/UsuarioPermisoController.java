package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itgt.pos.model.Usuario;
import com.itgt.pos.model.UsuarioPermiso;
import com.itgt.pos.service.UsuarioPermisoService;

@CrossOrigin
@RestController
@RequestMapping("/api/UsuarioPermiso/")
public class UsuarioPermisoController {
	@Autowired
	UsuarioPermisoService service;
	
	  HashMap<String, Object> mapG = new HashMap<String, Object>();
	  List<UsuarioPermiso> dataG = new ArrayList<>();
	  
	  @GetMapping("idUsuario/{idUsuario}")
	  public ResponseEntity<?> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    try {
	      dataG.clear();
	      dataG = service.getItemByUsuarioId(idUsuario, 1);
	      if (!dataG.isEmpty()) {
	        map.put("id", 1);
	        map.put("msj", "Elemento encontrados");
	        map.put("data", dataG);
	        return ResponseEntity.ok(map);
	      } else {
		        map.put("id", -1);
		        map.put("msj", "No tiene permisos");
		        map.put("data", dataG);
		        return ResponseEntity.ok(map);
	      }
	    } catch (Exception Ex) {
	      return new ResponseEntity<>(Ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  @PostMapping("")
	  public ResponseEntity<?> addItem(@RequestBody UsuarioPermiso item) {
	    ResponseEntity<?> response;
	    try {
	      dataG.clear();
	      UsuarioPermiso newItem = service.addItem(item);
	      dataG.add(newItem);
	      mapG.put("id", newItem.getId());
	      mapG.put("msj", "agregado exitosamente");
	      mapG.put("data", dataG);
	      response = ResponseEntity.ok(mapG);
	    } catch (Exception ex) {
	      response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return response;
	  }
	  
	  @DeleteMapping("id/{id}")
	  public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    try {
	      service.delItem(id);
	      map.put("id", 1);
	      map.put("msj", "Anulado Exisotamente");
	      return ResponseEntity.ok(map);
	    } catch (Exception ex) {
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
}
