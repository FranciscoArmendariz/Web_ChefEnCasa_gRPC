package com.unla.chefEnCasa.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.chefEnCasa.server.entity.RecetaIncompleta;

public interface RecetaIncompletaRepository extends JpaRepository<RecetaIncompleta, Long>{

    @Query("SELECT b FROM RecetaIncompleta b WHERE b.borrador.id = :idBorrador")
    List<RecetaIncompleta> findAllByBorradorId(@Param("idBorrador") long idBorrador);
    
}
