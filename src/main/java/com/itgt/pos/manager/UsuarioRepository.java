package com.itgt.pos.manager;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.itgt.pos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
  List<Usuario> findAll();
  @SuppressWarnings("unchecked")
  Usuario save(Usuario employee);
}  