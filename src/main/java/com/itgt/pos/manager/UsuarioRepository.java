package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itgt.pos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}