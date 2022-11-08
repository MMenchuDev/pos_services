package com.itgt.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.DetalleIngresoRepository;
import com.itgt.pos.model.DetalleIngreso;

@Service
public class DetalleIngresoService {
	
	@Autowired
	DetalleIngresoRepository repo;
	
	public DetalleIngreso addItem(DetalleIngreso data) throws Exception{
		DetalleIngreso item = new DetalleIngreso();
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
}
