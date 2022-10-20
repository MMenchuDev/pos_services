package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Egreso;

public interface EgresoRepository extends JpaRepository<Egreso, Long>{
	
}
