package com.unla.chefEnCasa.server.Soap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.chefEnCasa.server.Soap.model.RecetarioModel;

public interface RecetarioRespository extends JpaRepository<RecetarioModel, Long> {
    public List<RecetarioModel> findByIdUsuario(long idUsuario);
}
