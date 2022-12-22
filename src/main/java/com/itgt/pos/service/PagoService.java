package com.itgt.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.PagoRepository;
import com.itgt.pos.model.Pago;

@Service
public class PagoService {

	@Autowired
	private PagoRepository repo;
	
	public Pago addItem(Pago data) throws Exception{
		Pago item = new Pago();
		try { 
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
}
