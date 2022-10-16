package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long>{
	
}
