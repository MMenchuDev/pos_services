package com.itgt.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.ArticuloRepository;
import com.itgt.pos.model.Articulo;

@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository repo;
	
	public Articulo addItem(Articulo data) throws Exception{
		Articulo item = new Articulo();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
}
