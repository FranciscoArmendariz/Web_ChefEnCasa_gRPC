package com.unla.chefEnCasa.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.chefEnCasa.server.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsuario(String usuario);

}
