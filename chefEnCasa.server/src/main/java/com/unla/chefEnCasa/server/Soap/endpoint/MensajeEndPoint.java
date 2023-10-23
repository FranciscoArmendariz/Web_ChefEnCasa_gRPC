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
import com.chefencasa.demosoap.gen.Mensaje;
import com.chefencasa.demosoap.gen.PostMensajeRequest;
import com.chefencasa.demosoap.gen.PostMensajeResponse;
import com.chefencasa.demosoap.gen.PostMensajeRespuestaRequest;
import com.chefencasa.demosoap.gen.PostMensajeRespuestaResponse;
import com.unla.chefEnCasa.server.Soap.SoapService.MensajeService;
import com.unla.chefEnCasa.server.Soap.model.MensajeModel;
import com.unla.chefEnCasa.server.Soap.repository.MensajeRepository;
import com.unla.chefEnCasa.server.entity.Usuario;

import com.unla.chefEnCasa.server.repository.UsuarioRepository;


@Endpoint
public class MensajeEndPoint {

    @Autowired
    private MensajeRepository mensajeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired 
    private MensajeService mensajeService;
    private static final String NAMESPACE_URI = "http://www.chefEncasa.com/demosoap/gen";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postMensajeRequest")
    @ResponsePayload
    @Async
    public PostMensajeResponse crearMensaje(@RequestPayload PostMensajeRequest request) {
        PostMensajeResponse response=new PostMensajeResponse();
        System.out.println("postMensajeRequest");
        System.out.println(request);
        mensajeService.CrearMensaje(request);

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postMensajeRespuestaRequest")
    @ResponsePayload
    @Async
    public PostMensajeRespuestaResponse responderMensaje(@RequestPayload PostMensajeRespuestaRequest request) {
        PostMensajeRespuestaResponse response= new PostMensajeRespuestaResponse();
        System.out.println("responderMensaje");  
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
    @Async
    public GetTraerMensajeResponse traerMensajesPorRecetor(@RequestPayload GetTraerMensajePorReceptorRequest request) {
         GetTraerMensajeResponse response= new GetTraerMensajeResponse();
        List<MensajeModel> mensaje= mensajeService.traerMensajeModelsPorReceptor(request.getIdReceptor());
        List<Mensaje> mensajes=mensajeService.mapMensajesToMensajeResponses(mensaje);
        response.getMensajes().addAll(mensajes);
        return response;  

  
    }


}
