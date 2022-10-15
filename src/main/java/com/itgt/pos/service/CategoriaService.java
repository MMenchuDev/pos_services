package com.itgt.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.CategoriaRepository;
import com.itgt.pos.model.Categoria;

@Service
public class CategoriaService {
	
	// inyectamos el repositorio
	@Autowired
	private CategoriaRepository repositorio;
	
	public Categoria updCategoria(Categoria data) throws Exception{
		Categoria item;
		try {
			repositorio.save(data);	
			item = repositorio.findById(data.getId()).get();
		} catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
}
