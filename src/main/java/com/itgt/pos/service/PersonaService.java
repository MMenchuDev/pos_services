package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.PersonaRepository;
import com.itgt.pos.model.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository repo;
	
	public Persona addItem(Persona data) throws Exception{
		Persona item = new Persona();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	public List<Persona> getAll() throws Exception{
		List<Persona> items = new ArrayList<Persona>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Persona getItemById(Long id) throws Exception{
		Persona item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	public Persona updItem(Persona data) throws Exception{
		Persona item;
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
  
	public List<Persona> getAllProveedores() throws Exception{
		List<Persona> items = new ArrayList<Persona>();
		try {
			items = repo.findByTipopersonaAndEstado(2,1);	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public List<Persona> getClienteByNodoc(String nodocumento) throws Exception{
		List<Persona> items = new ArrayList<Persona>();
		try {
			items = repo.findByTipopersonaAndNodocumentoAndEstado(1,nodocumento,1);	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
  
}
