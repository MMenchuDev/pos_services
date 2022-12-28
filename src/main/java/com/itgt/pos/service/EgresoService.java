package com.itgt.pos.service;

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
	
	
	public List<Egreso> getByEstado(int estado, int tipoComprobante) throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			Date currentDate = new Date();
			items = repo.findByEstadoAndTipoComprobanteAndFechaegreso(estado,1, currentDate);	
		}catch(Exception ex) { 
			throw new Exception(ex.getMessage());
		}
		return items;
	}
}
