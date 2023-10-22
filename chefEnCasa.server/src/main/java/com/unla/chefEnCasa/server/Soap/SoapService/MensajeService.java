package com.unla.chefEnCasa.server.Soap.SoapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chefencasa.demosoap.gen.GetTraerMensajePorAutorRequest;
import com.chefencasa.demosoap.gen.GetTraerMensajeResponse;
import com.chefencasa.demosoap.gen.Mensaje;
import com.chefencasa.demosoap.gen.PostMensajeRequest;
import com.chefencasa.demosoap.gen.PostMensajeRespuestaRequest;
import com.unla.chefEnCasa.server.Soap.model.MensajeModel;
import com.unla.chefEnCasa.server.Soap.repository.MensajeRepository;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.RecetaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void CrearMensaje(PostMensajeRequest request) {
        boolean creado=false;
        MensajeModel mensaje = new MensajeModel();
        mensaje.setAsunto(request.getAsunto());
        mensaje.setAutor(usuarioRepository.findById(request.getIdAutor()).orElse(null));
        mensaje.setReceptor(usuarioRepository.findById(request.getIdReceptor()).orElse(null));
        mensaje.setMensaje(request.getMensaje());
        mensaje.setRespuesta(request.getRespuesta());
        System.out.println("String Mensaje"+mensaje);
        try {
            mensajeRepository.save(mensaje);
            creado=true;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(creado);
    }

    public void ResponderMensaje(PostMensajeRespuestaRequest request) {
        MensajeModel mensaje = mensajeRepository.findById(request.getMensajeId()).orElse(null);
        if (mensaje.getRespuesta() != null) {
            throw new ServerException("El mensaje ya tenia una respuesta", HttpStatus.BAD_REQUEST);
        } else {
            mensaje.setRespuesta(request.getRespuesta());
        }
        mensajeRepository.save(mensaje);

    }

    public List<Mensaje> traerMensajesPorAutor(long idAutor) {
        Usuario usuario = usuarioRepository.findById(idAutor).orElse(null);
        return mapMensajesToMensajeResponses(mensajeRepository.findByAutor(usuario));

    }

    public List<MensajeModel>traerMensajeModelsPorAutor(long idAutor){
        Usuario usuario = usuarioRepository.findById(idAutor).orElse(null);
        return mensajeRepository.findByAutor(usuario);
    }

    public List<Mensaje> traerMensajesPorReceptor(long idReceptor) {
        Usuario usuario = usuarioRepository.findById(idReceptor).orElse(null);
        return mapMensajesToMensajeResponses(mensajeRepository.findByReceptor(usuario));

    }
      public List<MensajeModel>traerMensajeModelsPorReceptor(long idAutor){
        Usuario usuario = usuarioRepository.findById(idAutor).orElse(null);
        return mensajeRepository.findByReceptor(usuario);
    }

    public List<Mensaje> mapMensajesToMensajeResponses(List<MensajeModel> mensajes) {
        List<Mensaje> mensajeResponses = new ArrayList<>();

        for (MensajeModel mensaje : mensajes) {
            Mensaje mensajeResponse = new Mensaje();
            mensajeResponse.setIdMensaje(mensaje.getId());
            mensajeResponse.setNombreAutor(mensaje.getAutor().getNombre());
            mensajeResponse.setNombreReceptor(mensaje.getReceptor().getNombre());
            mensajeResponse.setAsunto(mensaje.getAsunto());
            mensajeResponse.setMensaje(mensaje.getMensaje());
            mensajeResponse.setRespuesta(mensaje.getRespuesta());

            mensajeResponses.add(mensajeResponse);
        }

        return mensajeResponses;
    }


    public Mensaje  mapMensajeToMensajeResponses(MensajeModel mensaje){
        Mensaje mensajeResponse=new Mensaje();
        mensajeResponse.setIdMensaje(mensaje.getId());
        mensajeResponse.setNombreAutor(mensaje.getAutor().getNombre());
        mensajeResponse.setNombreReceptor(mensaje.getReceptor().getNombre());
        mensajeResponse.setAsunto(mensaje.getAsunto());
        mensajeResponse.setMensaje(mensaje.getMensaje());
        mensajeResponse.setRespuesta(mensaje.getRespuesta());

        return mensajeResponse;
    }

}
