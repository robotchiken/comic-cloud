package com.takuba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takuba.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByUsuario(String usuario);
}
