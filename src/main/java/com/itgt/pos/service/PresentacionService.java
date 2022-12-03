package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.PresentacionRepository;
import com.itgt.pos.model.Presentacion;

@Service
public class PresentacionService {
	
	@Autowired
	private PresentacionRepository repo;
	
	public List<Presentacion> getAll() throws Exception{
		List<Presentacion> items = new ArrayList<Presentacion>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Presentacion getItemById(Long id) throws Exception{
		Presentacion item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Presentacion addItem(Presentacion data) throws Exception{
		Presentacion item = new Presentacion();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Presentacion updItem(Presentacion data) throws Exception{
		Presentacion item;
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
