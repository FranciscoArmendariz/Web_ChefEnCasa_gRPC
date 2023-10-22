package com.unla.chefEnCasa.server.Soap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unla.chefEnCasa.server.Soap.model.MensajeModel;
import com.unla.chefEnCasa.server.entity.Usuario;

public interface MensajeRepository extends JpaRepository<MensajeModel, Long> {
    public List<MensajeModel> findByAutor(Usuario usuario);
    public List<MensajeModel> findByReceptor(Usuario usuario);
}
