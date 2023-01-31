package com.itgt.pos.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.DetalleEgreso;
import com.itgt.pos.model.Egreso;
import com.itgt.pos.model.Pago;
import com.itgt.pos.service.ArticuloService;
import com.itgt.pos.service.DetalleEgresoService;
import com.itgt.pos.service.EgresoService;
import com.itgt.pos.service.PagoService;

@CrossOrigin
@RestController
@RequestMapping("api/Egreso")
public class EgresoController {
	@Autowired
	EgresoService service;
	@Autowired
	DetalleEgresoService serviceExt;
	@Autowired
	ArticuloService serviceExtDos;
	@Autowired
	PagoService serviceTres;
	
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
	
	@GetMapping("codigocliente/{codigo}")
	public ResponseEntity<?> getEgresosCodigoCliente(@PathVariable("codigo") String codigo){
		ResponseEntity<?> response;
		try {
			dataG.clear();
			dataG = service.getEgresosCredito(0, codigo, 0);
			if (dataG.size() > 0) {
				mapG.put("id", 1);
				mapG.put("msj", "Datos obtenidos exitosamente");
				mapG.put("data", dataG);	
			}else {
				mapG.put("id", -1);
				mapG.put("msj", "Creditos no disponibles para el cliente: "+codigo);
				mapG.put("data", dataG);				
			}
			response = ResponseEntity.ok(mapG);
		} catch (Exception ex) {
			mapG.put("id", -1);
			mapG.put("msj", "Error al obtener los datos");
			mapG.put("data", dataG);
			response = ResponseEntity.ok(mapG); 
		}
		return response;
	}

	@PostMapping("")
	public ResponseEntity<?> addItem(@RequestBody Egreso item) {
		ResponseEntity<?> response;
		try {
			dataG.clear();
			// GUARDAR EL ENCABEZADO
			Egreso newItem = service.addItem(item);
			// GUARDO EL DETALLE
			for (DetalleEgreso i : item.getItems()) {
				i.setEgreso(newItem);
				serviceExt.addItem(i);
				
				Articulo element = serviceExtDos.getItemById(i.getArticulo().getId());
				element.setExistencia(element.getExistencia() - i.getCantidad());
				serviceExtDos.updItem(element);	
			}
			
			for(Pago i : item.getPagos()) {
				i.setEgreso(newItem);
				serviceTres.addItem(i);
			}
			
			// OBTENER EL ENCABEZADO Y DETALLE GUARDADO
			newItem = service.getItemById(newItem.getId());
			dataG.add(newItem);
			mapG.put("id", newItem.getId());
			mapG.put("msj", "agregado exitosamente");
			mapG.put("data", dataG);
			
			/*Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Hello World", font);

			document.add(chunk);
			document.close();*/
			
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
	      Egreso item = service.getItemById(id);
	      for(DetalleEgreso i : item.getItems()){
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

	    @PutMapping("")
	    public ResponseEntity<?> updItem(@RequestBody Egreso data){
	    	ResponseEntity<?> response;//= new ResponseEntity<>(null);
	    		try {
	    			dataG.clear();
	    			Egreso newItem = service.updItem(data);
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
	    
		@GetMapping("estado/{estado}/tipoComprobante/{tipoComprobante}/sucursal/{idSucursal}/tipopago/{tipopago}/egresosFechaActual")
		public ResponseEntity<?> getAllItems(@PathVariable("estado") int estado, @PathVariable("tipoComprobante") int tipoComprobante, @PathVariable("idSucursal") Long idSucursal, @PathVariable("tipopago") int tipopago) {
			ResponseEntity<?> response;
			try {
				dataG.clear();
				dataG = service.getByEstadoTipoComprobanteCurrentDate(estado, tipoComprobante, idSucursal, tipopago);
				mapG.put("id", dataG.size());
				Date currentDate = new Date();
				mapG.put("msj", "Datos obtenidos exitosamente"+currentDate);
				mapG.put("data", dataG);
				response = ResponseEntity.ok(mapG);
			} catch (Exception ex) {
				response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		}
		
		@GetMapping("estado/{estado}/tipoComprobante/{tipoComprobante}/fechaInicio/{fechainicio}/fechaFin/{fechafin}/sucursal/{idSucursal}")
		public ResponseEntity<?> getByEstadoTipoComprobanteCurrentDate(@PathVariable("estado") int estado, @PathVariable("tipoComprobante") int tipoComprobante, @PathVariable("fechainicio") String fechainicio, @PathVariable("fechafin") String fechafin, @PathVariable("idSucursal") Long idSucrusal) {
			ResponseEntity<?> response;
			try {
				dataG.clear();
				dataG = service.getByEstadoTipoComprobanteBetwen(estado, tipoComprobante, fechainicio, fechafin, idSucrusal);
				mapG.put("id", dataG.size());
				Date currentDate = new Date();
				mapG.put("msj", "Datos obtenidos exitosamente"+currentDate);
				mapG.put("data", dataG);
				response = ResponseEntity.ok(mapG);
			} catch (Exception ex) {
				response = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		}

		@GetMapping("creditos")
		public ResponseEntity<?> getEgresos(){
			ResponseEntity<?> response;
			try {
				dataG.clear();
				dataG = service.getEgresosCreditoAll(0, 0);
				if (dataG.size() > 0) {
					mapG.put("id", 1);
					mapG.put("msj", "Datos obtenidos exitosamente");
					mapG.put("data", dataG);	
				}else {
					mapG.put("id", -1);
					mapG.put("msj", "Creditos no disponibles para el cliente: ");
					mapG.put("data", dataG);				
				}
				response = ResponseEntity.ok(mapG);
			} catch (Exception ex) {
				mapG.put("id", -1);
				mapG.put("msj", "Error al obtener los datos");
				mapG.put("data", dataG);
				response = ResponseEntity.ok(mapG); 
			}
			return response;
		}		
		
		
		@GetMapping("dateexample/{datestring}/egresos")
		public ResponseEntity<?> getEgresosByDate(@PathVariable("datestring") String datestring){
			ResponseEntity<?> response;
			try {
				dataG.clear();
				dataG = service.getEgresosWithDate(datestring);
				if (dataG.size() > 0) {
					mapG.put("id", 1);
					mapG.put("msj", "Datos obtenidos exitosamente");
					mapG.put("data", dataG);	
				}else {
					mapG.put("id", -1);
					mapG.put("msj", "Creditos no disponibles para el cliente: ");
					mapG.put("data", dataG);				
				}
				response = ResponseEntity.ok(mapG);
			} catch (Exception ex) {
				mapG.put("id", -1);
				mapG.put("msj", "Error al obtener los datos");
				mapG.put("data", dataG);
				response = ResponseEntity.ok(mapG); 
			}
			return response;
		}
		
}
