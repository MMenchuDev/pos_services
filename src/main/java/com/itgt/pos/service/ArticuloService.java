package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.ArticuloRepository;
import com.itgt.pos.model.Articulo;
import com.itgt.pos.model.Articulo;
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
	public List<Articulo> getAll() throws Exception{
		List<Articulo> items = new ArrayList<Articulo>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Articulo getItemById(Long id) throws Exception{
		Articulo item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	
	public Articulo updItem(Articulo data) throws Exception{
		Articulo item;
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
	
	public Articulo getItemByCodigo(String codigo) throws Exception{
		Articulo item;
		try {
			item = repo.findByCodigoAndEstado(codigo,1).get(0);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
		return item;
	}
}
