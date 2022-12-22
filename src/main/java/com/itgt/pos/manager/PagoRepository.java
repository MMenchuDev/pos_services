package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long>{

}
