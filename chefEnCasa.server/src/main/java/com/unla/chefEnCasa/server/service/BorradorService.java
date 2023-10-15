package com.unla.chefEnCasa.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.entity.Borrador;
import com.unla.chefEnCasa.server.entity.RecetaIncompleta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.repository.BorradorRepository;
import com.unla.chefEnCasa.server.repository.RecetaIncompletaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class BorradorService {

    @Autowired
    private BorradorRepository borradorRepository;

    @Autowired
    private RecetaIncompletaRepository recetaIncompletaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean crearBorrador(List<RecetaIncompleta> borrador, long idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(null);

        Borrador borr = new Borrador();
        borr.setIdUsuario(usuario.getId());
        borradorRepository.save(borr);

        List<RecetaIncompleta> recetas = new ArrayList<>();
        for(RecetaIncompleta r : borrador){
            RecetaIncompleta nueva = new RecetaIncompleta();
            nueva.setTitulo(r.getTitulo());
            nueva.setDescripcion(r.getDescripcion());
            nueva.setCategoria(r.getCategoria());
            nueva.setTiempoAprox(r.getTiempoAprox());
            nueva.setBorrador(borr);
            recetas.add(nueva);
            recetaIncompletaRepository.save(nueva);
        }
        return true;
    }

    public List<Borrador> traerBorradoresPorUsuario(long idUsuario){ // SOLO LOS BORRADORES SIN LAS LISTAS DE RECETAS INCOMPLETAS
        return borradorRepository.findByIdUsuario(idUsuario);  
    }

    public List<RecetaIncompleta> traerBorrador(long idBorrador){
        return recetaIncompletaRepository.findAllByBorradorId(idBorrador);
    }
   
}
