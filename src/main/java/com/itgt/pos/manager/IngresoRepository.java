package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Ingreso;

public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

}
