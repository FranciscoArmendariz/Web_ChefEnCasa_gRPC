package com.unla.chefEnCasa.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.chefEnCasa.server.entity.Borrador;

public interface BorradorRepository extends JpaRepository<Borrador, Long>{

    public List<Borrador> findByIdUsuario(long idUsuario);
    
}
