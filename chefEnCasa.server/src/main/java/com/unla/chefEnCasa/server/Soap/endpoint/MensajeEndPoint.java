package com.unla.chefEnCasa.server.Soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.chefencasa.demosoap.gen.GetTraerMensajePorAutorRequest;
import com.chefencasa.demosoap.gen.GetTraerMensajePorReceptorRequest;
import com.chefencasa.demosoap.gen.GetTraerMensajeResponse;
import com.chefencasa.demosoap.gen.GetTraerRecetariosPorUsuarioRequest;
import com.chefencasa.demosoap.gen.GetTraerRecetariosPorUsuarioResponse;
import com.chefencasa.demosoap.gen.Mensaje;
import com.chefencasa.demosoap.gen.PostBorrarRecetarioRequest;
import com.chefencasa.demosoap.gen.PostBorrarRecetarioResponse;
import com.chefencasa.demosoap.gen.PostMensajeRequest;
import com.chefencasa.demosoap.gen.PostMensajeResponse;
import com.chefencasa.demosoap.gen.PostMensajeRespuestaRequest;
import com.chefencasa.demosoap.gen.PostMensajeRespuestaResponse;
import com.chefencasa.demosoap.gen.PostRecetarioRequest;
import com.chefencasa.demosoap.gen.PostRecetarioResponse;
import com.unla.chefEnCasa.server.Soap.SoapService.MensajeService;
import com.unla.chefEnCasa.server.Soap.SoapService.RecetarioService;
import com.unla.chefEnCasa.server.Soap.model.MensajeModel;
import com.unla.chefEnCasa.server.Soap.model.RecetarioModel;
import com.unla.chefEnCasa.server.Soap.repository.MensajeRepository;
import com.unla.chefEnCasa.server.Soap.repository.RecetarioRespository;
import com.unla.chefEnCasa.server.entity.Usuario;

import com.unla.chefEnCasa.server.repository.UsuarioRepository;


@Endpoint
public class MensajeEndPoint {

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private RecetarioRespository recetarioRespository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired 
    private MensajeService mensajeService;
    @Autowired
    private RecetarioService recetarioService;
    private static final String NAMESPACE_URI = "http://www.chefEncasa.com/demosoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postMensajeRequest")
    @ResponsePayload
    public PostMensajeResponse crearMensaje(@RequestPayload PostMensajeRequest request) {
        PostMensajeResponse response=new PostMensajeResponse();
        System.out.println(request);  
        mensajeService.CrearMensaje(request);

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postMensajeRespuestaRequest")
    @ResponsePayload
    public PostMensajeRespuestaResponse responderMensaje(@RequestPayload PostMensajeRespuestaRequest request) {
        PostMensajeRespuestaResponse response= new PostMensajeRespuestaResponse();
        MensajeModel mensaje=mensajeRepository.findById(request.getMensajeId()).orElse(null);
        mensaje.setRespuesta(request.getRespuesta());
        mensajeRepository.save(mensaje);
        response.setMensaje(mensajeService.mapMensajeToMensajeResponses(mensaje));
        return response;

  
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTraerMensajePorAutorRequest")
    @ResponsePayload
    @Async
    public GetTraerMensajeResponse traerMensajesPorAutor(@RequestPayload GetTraerMensajePorAutorRequest request) {
         GetTraerMensajeResponse response= new GetTraerMensajeResponse();
        List<MensajeModel> mensaje= mensajeService.traerMensajeModelsPorAutor(request.getIdAutor());
        List<Mensaje> mensajes=mensajeService.mapMensajesToMensajeResponses(mensaje);
        response.getMensajes().addAll(mensajes);
        return response;  

  
    }
       
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTraerMensajePorReceptorRequest")
    @ResponsePayload
    public GetTraerMensajeResponse traerMensajesPorRecetor(@RequestPayload GetTraerMensajePorReceptorRequest request) {
         GetTraerMensajeResponse response= new GetTraerMensajeResponse();
        List<MensajeModel> mensaje= mensajeService.traerMensajeModelsPorReceptor(request.getIdReceptor());
        List<Mensaje> mensajes=mensajeService.mapMensajesToMensajeResponses(mensaje);
        response.getMensajes().addAll(mensajes);
        return response;  

  
    }

    /*@PayloadRoot(namespace=NAMESPACE_URI, localPart = "postRecetarioRequest")
    @ResponsePayload
    public PostRecetarioResponse crearRecetario(@RequestPayload PostRecetarioRequest request){
        PostRecetarioResponse response= new PostRecetarioResponse();
        response.setMensaje(false);
        RecetarioModel recetario= new RecetarioModel();
        try{
        recetario.setIdUsuario(request.getIdAutor());
        recetario.setNombre(request.getNombre());
        recetarioRespository.save(recetario);
        response.setMensaje(true);
        }catch(Exception e){
            System.out.println(e);

        }
        return response;
    }
    @PayloadRoot(namespace=NAMESPACE_URI, localPart = "postBorrarRecetarioRequest")
    @ResponsePayload
    public PostBorrarRecetarioResponse borrarRecetario(@RequestPayload PostBorrarRecetarioRequest request){
        PostBorrarRecetarioResponse response= new PostBorrarRecetarioResponse();
        response.setMensaje(false);
        try{
        recetarioRespository.deleteById(request.getRecetarioId());
        response.setMensaje(true);
        }catch(Exception e){
            System.out.println(e);

        }
        return response;
    }

    @PayloadRoot(namespace=NAMESPACE_URI, localPart = "getTraerRecetariosPorUsuarioRequest")
    @ResponsePayload
    public GetTraerRecetariosPorUsuarioResponse traerRecetariosPorUsuario(@RequestPayload GetTraerRecetariosPorUsuarioRequest request){
        List<RecetarioModel> lista= recetarioRespository.findByIdUsuario(request.getIdUsuario());
        GetTraerRecetariosPorUsuarioResponse response= new GetTraerRecetariosPorUsuarioResponse();
        return response.getRecetarios().addAll(recetarioService.recetarioModelListaArecetarioRequestLista(lista));
    }*/



}
