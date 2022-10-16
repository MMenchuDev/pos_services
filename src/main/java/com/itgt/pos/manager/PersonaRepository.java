package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itgt.pos.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
