package com.itgt.pos.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.EgresoRepository;
import com.itgt.pos.model.Egreso;
import com.itgt.pos.model.Pago;
import com.itgt.pos.model.Egreso;

@Service
public class EgresoService {
	
	@Autowired
	private EgresoRepository repo;
	
	@Autowired
	PagoService serviceTres;

	public List<Egreso> getAll() throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Egreso getItemById(Long id) throws Exception{
		Egreso item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Egreso addItem(Egreso data) throws Exception{
		Egreso item = new Egreso();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public void delItem(Long id) throws Exception{
		try {
			repo.deleteById(id);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}	
	
	public List<Egreso> getEgresosCredito(int param, String nit, float paramtwo) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			items = repo.findByPersonaNodocumentoAndTipopagoAndPagopendienteGreaterThan(nit, param, paramtwo);
			return items;
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Egreso updItem(Egreso data) throws Exception{
		Egreso item;
		try {
			item = repo.save(data);
			for(Pago i : data.getPagos()) {
				serviceTres.addItem(i);
			}
		} catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	
	public List<Egreso> getByEstadoTipoComprobanteCurrentDate(int estado, int tipoComprobante, Long id, int tipopago) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			Date currentDate = new Date();
	        // Specify format as "yyyy-MM-dd"
	        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
	        // Use format method on SimpleDateFormat
	        String formattedDateStr = dmyFormat.format(currentDate);
	        //System.out.println("Formatted Date in String format: "+formattedDateStr);
			items = repo.findAllWithFechaegreso(formattedDateStr,estado,tipopago,tipoComprobante,id);
		}catch(Exception ex) { 
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public List<Egreso> getByEstadoTipoComprobanteBetwen(int estado, int tipoComprobante, String start, String end, Long idSucursal) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);

			items = repo.findAllWithFechaegresoBetween(sdf.format(startDate), sdf.format(endDate),estado,tipoComprobante,idSucursal);

		}catch(Exception ex) { 
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	
	public List<Egreso> getEgresosCreditoAll(int param, float paramtwo) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			items = repo.findByTipopagoAndPagopendienteGreaterThan(param, paramtwo);
			return items;
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Egreso> getEgresosWithDate(String datestring) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			items = repo.findAllWithFechaegreso(datestring,1,1,1,(long)1);
			return items;
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
