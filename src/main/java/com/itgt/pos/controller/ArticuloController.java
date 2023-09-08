package com.itgt.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.service.ArticuloService;
import com.itgt.pos.utils.JsonResult;

@CrossOrigin //  (originPatterns = {"http://localhost:3000"}) acceso a datos React
@RestController
@RequestMapping("api/Articulo")
public class ArticuloController {
	private HttpHeaders headers;
	@Autowired
	ArticuloService service;
    HashMap<String, Object> mapG = new HashMap<String, Object>();
    List<Articulo> dataG = new ArrayList<>();
	@Autowired
	private RestTemplate restTemplate; 	
    
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
    
    @GetMapping("{id}/existencia")
    public ResponseEntity<?> getExistenciaArticulo(@PathVariable("id") Long id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
        	dataG.clear();
            Articulo item = service.getItemById(id);
            
            //obtener las compras
        	HttpEntity<JsonResult> request = new HttpEntity<>(headers);
        	String url = generateUrl("/Ingreso/estado/1/all"); //compras activas
            ResponseEntity<JsonResult> result = restTemplate.exchange(url, HttpMethod.GET, request, JsonResult.class);
            
            
            //obtener las compras
        	HttpEntity<JsonResult> requestVentas = new HttpEntity<>(headers);
        	String urlVetas = generateUrl("/Egreso/estado/1/all"); //compras activas
            ResponseEntity<JsonResult> resultVentas = restTemplate.exchange(urlVetas, HttpMethod.GET, requestVentas, JsonResult.class);
     
            
            //Obtener el total de ingresos del presente producto
            float existencia = 0;
            existencia = service.obtieneExistenciaProducto(id.intValue(), result.getBody().data);
            //result.getBody().data.forEach((n) -> System.out.println(n.get("items")));
            
            
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
    
    @GetMapping("codigo/{codigo}")
    public ResponseEntity<?> getArticuloByCodigo(@PathVariable("codigo") String codigo){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
        	dataG.clear();
            Articulo item = service.getItemByCodigo(codigo);
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
        	map.put("id", -1);
        	map.put("msj", "Elemento encontrado");
        	return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }    
    @GetMapping("activos")
    public ResponseEntity<?> getItemByEstado(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
			dataG.clear();
			dataG = service.getItemByEstadoActivo();
            if(dataG.size() > 0) {
            	map.put("id", dataG.size());
            	map.put("msj", "Elemento encontrados");
            	map.put("data", dataG);
            	return ResponseEntity.ok(map);
            }else {
            	return ResponseEntity.noContent().build();
            }         	
        }catch(Exception Ex) {
        	map.put("id", -1);
        	map.put("msj", "Elemento encontrado");
        	return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }  
	private String generateUrl(String url) {
		String urlComplete = "http://localhost:8080/api"+url;
		return urlComplete;
	}
}
