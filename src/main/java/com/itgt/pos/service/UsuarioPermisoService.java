package com.itgt.pos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.UsuarioPermisoRepository;
import com.itgt.pos.model.UsuarioPermiso;

@Service
public class UsuarioPermisoService {

	@Autowired
	private UsuarioPermisoRepository repo;
	
	public List<UsuarioPermiso> getItemByUsuarioId(Long id, int estado) throws Exception{
		List<UsuarioPermiso> item;
		try {
			item = repo.findByUsuarioIdAndEstado(id, estado);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}	
}
