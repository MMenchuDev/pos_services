package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.IngresoRepository;
import com.itgt.pos.model.Ingreso;

@Service
public class IngresoService {

	@Autowired
	private IngresoRepository repo;
	
	public List<Ingreso> getAll() throws Exception{
		List<Ingreso> items = new ArrayList<Ingreso>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Ingreso getItemById(Long id) throws Exception{
		Ingreso item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Ingreso addItem(Ingreso data) throws Exception{
		Ingreso item = new Ingreso();
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
