package com.unla.chefEnCasa.server.Soap.SoapService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chefencasa.demosoap.gen.RecetarioSoap;
import com.unla.chefEnCasa.server.Soap.model.MensajeModel;
import com.unla.chefEnCasa.server.Soap.model.RecetarioModel;

@Service
public class RecetarioService {
    public RecetarioModel recetarioRequestArecetarioModel(RecetarioSoap recetario){
        RecetarioModel model= new RecetarioModel();
        model.setIdUsuario(recetario.getIdUsuario());
        model.setNombre(recetario.getNombre());
        return model;
    }

    public RecetarioSoap recetarioModelArecetarioRequest(RecetarioModel recetario){
        RecetarioSoap model=new RecetarioSoap();
        model.setIdRecetario(recetario.getId());
        model.setIdUsuario(recetario.getIdUsuario());
        model.setNombre(recetario.getNombre());
        return model;
    }

    public List<RecetarioSoap> recetarioModelListaArecetarioRequestLista(List<RecetarioModel> lista){
        List<RecetarioSoap> response= new ArrayList<>();
        for(RecetarioModel r : lista){
            RecetarioSoap recetario=new RecetarioSoap();
            recetario.setIdRecetario(r.getId());
            recetario.setIdUsuario(r.getIdUsuario());
            recetario.setNombre(r.getNombre());
            response.add(recetario);
        }
        
        return response;


    }
  
}
