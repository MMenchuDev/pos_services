package com.itgt.pos.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itgt.pos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByEstadoAndUsuarioAndPassword(String estado, String usuario, String password);
}