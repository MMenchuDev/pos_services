package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.EgresoRepository;
import com.itgt.pos.model.Egreso;

@Service
public class EgresoService {
	
	@Autowired
	private EgresoRepository repo;

	public List<Egreso> getAll() throws Exception{
		List<Egreso> items = new ArrayList<Egreso>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
}
