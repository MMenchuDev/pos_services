package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String>{
    
	//List<Categoria> findAll();
    
	//Crea una nueva categoria
	@SuppressWarnings("unchecked")
	Categoria save(Categoria categoria);
	
	//Debuelve todas las categorias
	List<Categoria> findAll();
	
	//elimina una categoria por su id
	void deleteById(Long id);
	
	
}
