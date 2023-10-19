package com.unla.chefEnCasa.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.entity.Denuncia;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.DenunciaRepository;
import com.unla.chefEnCasa.server.repository.RecetaRepository;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    public boolean crearDenuncia(long idReceta, String motivo) {

        Receta receta = recetaRepository.findById(idReceta)
                .orElseThrow(() -> new ServerException("no existe una receta con ese id", HttpStatus.NOT_FOUND));
        Denuncia denuncia = new Denuncia();
        denuncia.setEstado("nuevo");
        denuncia.setMotivo(motivo);
        denuncia.setReceta(receta);

        try {
            denunciaRepository.save(denuncia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Denuncia> traerDenuncias() {
        return denunciaRepository.findAll();
    }

    public boolean resolverDenuncia(long idDenuncia, boolean eliminar) {
        Denuncia denuncia = denunciaRepository.findById(idDenuncia).orElseThrow(() -> new ServerException("No existe una denuncia con ese id", HttpStatus.BAD_REQUEST));
        
        if(eliminar){
            denuncia.setEstado("resuelta");
            Receta receta = recetaRepository.findById(denuncia.getReceta().getId()).orElseThrow(() -> new ServerException("No existe una receta con ese id", HttpStatus.BAD_REQUEST));
            receta.setActiva(false); //de momento manejamos borrado logico
            recetaRepository.save(receta);
        }else{
            denuncia.setEstado("resuelta");
        }
        try{
            denunciaRepository.save(denuncia);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
