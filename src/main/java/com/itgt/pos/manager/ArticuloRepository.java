package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Articulo;

public interface ArticuloRepository extends JpaRepository<Articulo, Long>{
	List<Articulo> findByCodigoAndEstado(String codigo, int estado);
	
	List<Articulo> findByEstadoOrderByExistenciaAsc(int estado);
}
