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

import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.DetalleIngreso;
import com.itgt.pos.model.Ingreso;
import com.itgt.pos.service.ArticuloService;
import com.itgt.pos.service.DetalleIngresoService;
import com.itgt.pos.service.IngresoService;

@CrossOrigin
@RestController
@RequestMapping("api/Ingreso")
public class IngresoController {
	
	@Autowired
	IngresoService service;
	@Autowired
	DetalleIngresoService serviceExt;
	@Autowired
	ArticuloService serviceExtDos;
	
	HashMap<String, Object> mapG = new HashMap<String, Object>();
	List<Ingreso> dataG = new ArrayList<>();	

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
	public ResponseEntity<?> getIngresoById(@PathVariable("id") Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			dataG.clear();
			Ingreso item = service.getItemById(id);
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

	@PostMapping("")
	public ResponseEntity<?> addItem(@RequestBody Ingreso item) {
		ResponseEntity<?> response;
		try {
			dataG.clear();
			// GUARDAR EL ENCABEZADO
			Ingreso newItem = service.addItem(item);
			// GUARDO EL DETALLE
			for (DetalleIngreso i : item.getItems()) {
				i.setId(newItem.getId());
				serviceExt.addItem(i);
				
				//Actualizar existencia
				//1.Obtener el producto que se está comprando
				Articulo art = serviceExtDos.getItemById(i.getArticulo().getId());
				//2. Actualizar existencia
				art.setExistencia(art.getExistencia()+i.getCantidad());
				serviceExtDos.updItem(art);
			}
			// OBTENER EL ENCABEZADO Y DETALLE GUARDADO
			newItem = service.getItemById(newItem.getId());
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
	      Ingreso item = service.getItemById(id);
	      for(DetalleIngreso i : item.getItems()){
	    	  serviceExt.delItem(i.getId());
	      }
	      service.delItem(id);
	      map.put("id", 1);
	      map.put("msj", "Anulado Exisotamente");
	      return ResponseEntity.ok(map);
	    } catch (Exception ex) {
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
