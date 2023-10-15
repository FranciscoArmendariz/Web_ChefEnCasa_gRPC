package com.unla.chefEnCasa.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.chefEnCasa.server.entity.Borrador;
import com.unla.chefEnCasa.server.entity.RecetaIncompleta;

public interface RecetaIncompletaRepository extends JpaRepository<RecetaIncompleta, Long>{

    @Query("SELECT b FROM RecetaIncompleta b WHERE b.borrador.id = :idBorrador")
    List<RecetaIncompleta> findAllByBorradorId(@Param("idBorrador") long idBorrador);
    @Transactional
	@Modifying
    @Query("DELETE FROM IngredienteIncompleto i WHERE i.recetaIncompleta = :receta")
	public void deleteIngredientesByRecetaId(@Param("receta") RecetaIncompleta receta);
	@Transactional
	@Modifying
    @Query("DELETE FROM FotoIncompleta i WHERE i.recetaIncompleta = :receta")
	public void deleteFotosByRecetaId(@Param("receta") RecetaIncompleta receta);
	@Transactional
	@Modifying
    @Query("DELETE FROM PasoIncompleto i WHERE i.recetaIncompleta = :receta")
	public void deletePasosByRecetaId(@Param("receta") RecetaIncompleta receta);

	@Transactional
	public void deleteByBorrador(Borrador borrador);
    
}
