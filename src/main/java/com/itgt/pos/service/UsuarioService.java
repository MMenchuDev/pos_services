package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgt.pos.manager.UsuarioRepository;
import com.itgt.pos.model.Persona;
import com.itgt.pos.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public List<Usuario> getAll() throws Exception{
		List<Usuario> items = new ArrayList<Usuario>();
		try {
			items = repo.findAll();	
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
	
	public Usuario getItemById(Long id) throws Exception{
		Usuario item;
		try {
			item = repo.findById(id).get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Usuario addItem(Usuario data) throws Exception{
		Usuario item = new Usuario();
		try {
			item = repo.save(data);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return item;
	}
	
	public Usuario updItem(Usuario data) throws Exception{
		Usuario item;
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
	
	
	public List<Usuario> getItemByEstadoUsuarioPassword(String usuario, String password) throws Exception{
		List<Usuario> items = new ArrayList<Usuario>();
		try {
			items = repo.findByEstadoAndUsuarioAndPassword("1", usuario, password);
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return items;
	}
}
