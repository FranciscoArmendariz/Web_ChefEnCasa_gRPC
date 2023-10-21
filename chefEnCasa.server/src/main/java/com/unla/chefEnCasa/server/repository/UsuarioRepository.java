package com.unla.chefEnCasa.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.entity.Receta;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
	public List<Receta> findRecetasCreadasById(long id);

	@Query("SELECT u FROM Usuario u JOIN u.recetasCreadas r WHERE r.id = :recetaId")
	public Usuario findCreadorByRecetaId(@Param("recetaId") Long recetaId);

}
