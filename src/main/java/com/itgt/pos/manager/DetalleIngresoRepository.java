package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.DetalleIngreso;

public interface DetalleIngresoRepository extends JpaRepository<DetalleIngreso, Long>{

}
