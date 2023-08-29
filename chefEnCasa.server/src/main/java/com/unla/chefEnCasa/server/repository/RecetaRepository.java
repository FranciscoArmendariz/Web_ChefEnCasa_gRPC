package com.unla.chefEnCasa.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.chefEnCasa.server.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{
	
	public Page<Receta> findByTituloContaining(String titulo, Pageable pageable);
	public Page<Receta> findByCategoriaContaining(String titulo, Pageable pageable);

}
