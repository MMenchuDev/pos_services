package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Ingreso;

public interface IngresoRepository extends JpaRepository<Ingreso, Long>{
	List<Ingreso> findByEstado(int estado);
}
