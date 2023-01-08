package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.PermisoRepository;
import com.itgt.pos.model.Permiso;

@Service
public class PermisoService {
	@Autowired
	private PermisoRepository repo;
	
	public List<Permiso> getAll() throws Exception{
		List<Permiso> items = new ArrayList<Permiso>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
}
