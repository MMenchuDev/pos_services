package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
	List<Persona> findByTipopersonaAndEstado(int tipopersona, int estado);
	List<Persona> findByTipopersonaAndNodocumentoAndEstado(int tipopersona, String nodocumento, int estado);	
}
