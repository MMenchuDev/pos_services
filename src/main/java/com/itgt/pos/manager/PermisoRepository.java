package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Long>{
	
}
