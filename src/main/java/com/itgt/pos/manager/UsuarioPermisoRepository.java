package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.UsuarioPermiso;

public interface UsuarioPermisoRepository extends JpaRepository<UsuarioPermiso, Long>{
	
	List<UsuarioPermiso> findByUsuarioIdAndEstado(Long id, int estado);
}
