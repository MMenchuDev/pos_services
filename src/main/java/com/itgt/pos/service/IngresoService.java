package com.itgt.pos.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
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
	
	public List<Ingreso> getAllByEstado(int idEstado) throws Exception{
		List<Ingreso> items = new ArrayList<Ingreso>();
		try {
			items = repo.findByEstado(idEstado);	
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

	public List<HashMap<String, Object>> getProductos(List<HashMap<String, Object>> productos){
		List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
		try {
			//1 obtener las compras y filtrar las activas
			List<Ingreso> ingresos = this.getAll();
			CollectionUtils.filter(ingresos, p -> ((Ingreso) p).getEstado() == 1);
			
			//2. 
			System.out.println(ingresos.size());
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		};
		
		
		return items;
	}
}
