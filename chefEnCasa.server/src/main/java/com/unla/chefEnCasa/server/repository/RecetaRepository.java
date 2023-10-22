package com.unla.chefEnCasa.server.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.unla.chefEnCasa.server.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>{
	
	public Page<Receta> findByTituloContainingAndActivaIsTrue(String titulo, Pageable pageable);
	public Page<Receta> findByCategoriaContainingAndActivaIsTrue(String titulo, Pageable pageable);
	public Page<Receta> findByTiempoAproxBetween(int minTiempoAprox, int maxTiempoAprox, Pageable pageable);
	@Query("SELECT DISTINCT r FROM Receta r JOIN r.ingredientes i WHERE i.nombre IN :nombresIngredientes")
    public Page<Receta> findByNombresIngredientes(@Param("nombresIngredientes") String nombresIngredientes, Pageable pageable);
	@Query("SELECT DISTINCT r FROM Receta r JOIN r.ingredientes i WHERE i.nombre IN :nombresIngredientes AND r.tiempoAprox BETWEEN :minTiempoAprox AND :maxTiempoAprox")
	public Page<Receta> findByNombresIngredientesAndTiempoAprox(@Param("nombresIngredientes") String nombresIngredientes,@Param("minTiempoAprox")int minTiempoAprox,
	@Param("maxTiempoAprox")int maxTiempoAprox,Pageable pageable);
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
	public List<Receta> findByActiva(boolean activa);
	public Page<Receta> findByActiva(boolean activa, Pageable pageable);

}
