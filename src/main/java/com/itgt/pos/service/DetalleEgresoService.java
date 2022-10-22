package com.itgt.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.DetalleEgresoRepository;
import com.itgt.pos.model.DetalleEgreso;

@Service
public class DetalleEgresoService {
	
	@Autowired
	private DetalleEgresoRepository repo;
	
	public DetalleEgreso addItem(DetalleEgreso data) throws Exception{
		DetalleEgreso item = new DetalleEgreso();
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
