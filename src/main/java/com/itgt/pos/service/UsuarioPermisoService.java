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
			item = repo.findByUsuarioIdAndEstadoOrderByPermisoDesc(id, estado);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}	
	public UsuarioPermiso addItem(UsuarioPermiso data) throws Exception{
		UsuarioPermiso item = new UsuarioPermiso();
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
