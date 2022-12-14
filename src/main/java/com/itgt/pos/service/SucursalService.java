package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.SucursalRepository;
import com.itgt.pos.model.Sucursal;

@Service
public class SucursalService {

	@Autowired
	private SucursalRepository repo;
	
	public List<Sucursal> getAll() throws Exception{
		List<Sucursal> items = new ArrayList<Sucursal>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Sucursal getItemById(Long id) throws Exception{
		Sucursal item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Sucursal addItem(Sucursal data) throws Exception{
		Sucursal item = new Sucursal();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Sucursal updItem(Sucursal data) throws Exception{
		Sucursal item;
		try {
			item = repo.save(data);
		} catch(Exception ex) {
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
