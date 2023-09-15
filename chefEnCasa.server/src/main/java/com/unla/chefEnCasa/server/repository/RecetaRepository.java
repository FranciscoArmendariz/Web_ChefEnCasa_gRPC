package com.unla.chefEnCasa.server.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.chefEnCasa.server.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{
	
	public Page<Receta> findByTituloContaining(String titulo, Pageable pageable);
	public Page<Receta> findByCategoriaContaining(String titulo, Pageable pageable);

	@Transactional
	@Modifying
    @Query("DELETE FROM Ingrediente i WHERE i.receta.id = :recetaId")
	public void deleteIngredientesByRecetaId(@Param("recetaId") long id);
	@Transactional
	@Modifying
    @Query("DELETE FROM Foto i WHERE i.receta.id = :recetaId")
	public void deleteFotosByRecetaId(@Param("recetaId") long id);
	@Transactional
	@Modifying
    @Query("DELETE FROM Paso i WHERE i.receta.id = :recetaId")
	public void deletePasosByRecetaId(@Param("recetaId") long id);

}
