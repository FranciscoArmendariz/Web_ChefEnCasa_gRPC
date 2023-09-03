package com.unla.chefEnCasa.server.grpcServicioImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.grpc.Empty2;
import com.unla.chefEnCasa.grpc.Foto;
import com.unla.chefEnCasa.grpc.Ingrediente;
import com.unla.chefEnCasa.grpc.ListaIngredientes;
import com.unla.chefEnCasa.grpc.Paso;
import com.unla.chefEnCasa.grpc.RecetaRequest;
import com.unla.chefEnCasa.grpc.RecetaRequestById;
import com.unla.chefEnCasa.grpc.RecetaRequestFilter;
import com.unla.chefEnCasa.grpc.RecetaResponse;
import com.unla.chefEnCasa.grpc.RecetaUpdateRequest;
import com.unla.chefEnCasa.grpc.UsuarioRequestByUserId;
import com.unla.chefEnCasa.grpc.getRecetaCreada;
import com.unla.chefEnCasa.grpc.getRecetaEditada;
import com.unla.chefEnCasa.grpc.getRecetas;
import com.unla.chefEnCasa.grpc.recetaGrpc.recetaImplBase;
import com.unla.chefEnCasa.server.dto.RecetaRequestDto;
import com.unla.chefEnCasa.server.dto.RecetaResponseDto;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.service.RecetaService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class RecetaGrpcImpl extends recetaImplBase {

    @Autowired
    private RecetaService recetaService;

    @Override
    public void crearReceta(RecetaRequest request, StreamObserver<getRecetaCreada> responseObserver) {
        RecetaRequestDto receta = new RecetaRequestDto();
        receta.setTitulo(request.getTitulo());
        receta.setCategoria(request.getCategoria());
        receta.setDescripcion(request.getDescripcion());
        receta.setTiempoAprox(request.getTiempoAprox());
        List<com.unla.chefEnCasa.server.entity.Foto> lstFotos = new ArrayList<>();
        for(Foto f : request.getFotosList()) {
            com.unla.chefEnCasa.server.entity.Foto foto = new com.unla.chefEnCasa.server.entity.Foto();
            foto.setUrl(f.getUrl());
            lstFotos.add(foto);
        }
        receta.setFotos(lstFotos);
        List<com.unla.chefEnCasa.server.entity.Ingrediente> lstIngredientes = new ArrayList<>();
        for (Ingrediente i : request.getIngredientesList()) {
            com.unla.chefEnCasa.server.entity.Ingrediente ingrediente = new com.unla.chefEnCasa.server.entity.Ingrediente();
            ingrediente.setNombre(i.getNombre());
            ingrediente.setCantidad(i.getCantidad());
            lstIngredientes.add(ingrediente);
        }
        receta.setIngredientes(lstIngredientes);
        List<com.unla.chefEnCasa.server.entity.Paso> lstPasos = new ArrayList<>();
        for (Paso p : request.getPasosList()) {
            com.unla.chefEnCasa.server.entity.Paso paso = new com.unla.chefEnCasa.server.entity.Paso();
            paso.setNumero(p.getNumero());
            paso.setDescripcion(p.getDescripcion());
            lstPasos.add(paso);
        }
        receta.setPasos(lstPasos);
        try {
            boolean response = recetaService.crearReceta(receta, request.getIdUsuario());
            getRecetaCreada recetaResponse = getRecetaCreada.newBuilder()
                    .setCreada(response)
                    .build();
            responseObserver.onNext(recetaResponse);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

    @Override
    public void editarReceta(RecetaUpdateRequest request, StreamObserver<getRecetaEditada> responseObserver) {
        RecetaRequestDto receta = new RecetaRequestDto();
        receta.setTitulo(request.getTitulo());
        receta.setCategoria(request.getCategoria());
        receta.setDescripcion(request.getDescripcion());
        receta.setTiempoAprox(request.getTiempoAprox());

        List<com.unla.chefEnCasa.server.entity.Foto> lstFotos = new ArrayList<>();
        for(Foto f : request.getFotosList()) {
            com.unla.chefEnCasa.server.entity.Foto foto = new com.unla.chefEnCasa.server.entity.Foto();
            foto.setUrl(f.getUrl());
            lstFotos.add(foto);
        }
        receta.setFotos(lstFotos);
        List<com.unla.chefEnCasa.server.entity.Ingrediente> lstIngredientes = new ArrayList<>();
        for (Ingrediente i : request.getIngredientesList()) {
            com.unla.chefEnCasa.server.entity.Ingrediente ingrediente = new com.unla.chefEnCasa.server.entity.Ingrediente();
            ingrediente.setNombre(i.getNombre());
            ingrediente.setCantidad(i.getCantidad());
            lstIngredientes.add(ingrediente);
        }
        receta.setIngredientes(lstIngredientes);
        List<com.unla.chefEnCasa.server.entity.Paso> lstPasos = new ArrayList<>();
        for (Paso p : request.getPasosList()) {
            com.unla.chefEnCasa.server.entity.Paso paso = new com.unla.chefEnCasa.server.entity.Paso();
            paso.setNumero(p.getNumero());
            paso.setDescripcion(p.getDescripcion());
            lstPasos.add(paso);
        }
        receta.setPasos(lstPasos);
        try {
            boolean response = recetaService.editarReceta(receta, request.getIdReceta());
            getRecetaEditada recetaResponse = getRecetaEditada.newBuilder()
                    .setEditada(response)
                    .build();
            responseObserver.onNext(recetaResponse);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

    @Override
    public void traerRecetasPorId(UsuarioRequestByUserId request, StreamObserver<getRecetas> responseObserver) {
      try{
        List <RecetaResponseDto> traerRecetas=recetaService.traerRecetasPorId(request.getId());
        List <RecetaResponse>recetaGrpcList=new ArrayList<>();
        
       for(int i=0;i<traerRecetas.size();i++){
        List<Ingrediente>ingredienteData=new ArrayList<>();
        List<Paso>pasoData=new ArrayList<>();
        List<Foto>fotoData=new ArrayList<>();
        for(int j=0; j<traerRecetas.get(i).getIngredientes().size();j++){
            Ingrediente ingredienteGrpcAdd=Ingrediente.newBuilder()
            .setCantidad(traerRecetas.get(i).getIngredientes().get(j).getCantidad())
            .setNombre(traerRecetas.get(i).getIngredientes().get(j).getNombre())
            .build();
            ingredienteData.add(ingredienteGrpcAdd);
        }
        for(int y=0; y<traerRecetas.get(i).getPasos().size();y++){
            Paso pasoGrpcAdd=Paso.newBuilder()
            .setDescripcion(traerRecetas.get(i).getPasos().get(y).getDescripcion())
            .setNumero(traerRecetas.get(i).getPasos().get(y).getNumero())
            .build();
            pasoData.add(pasoGrpcAdd);
        }
         for(int z=0;z<traerRecetas.get(i).getFotos().size();z++){
            Foto fotoGrpcAdd=Foto.newBuilder()
            .setUrl(traerRecetas.get(i).getFotos().get(z).getUrl())
            .build();
            fotoData.add(fotoGrpcAdd);

        }
        RecetaResponse  response=RecetaResponse.newBuilder()
        .setId(traerRecetas.get(i).getId())
        .setCategoria(traerRecetas.get(i).getCategoria())
        .setDescripcion(traerRecetas.get(i).getDescripcion())
        .setTiempoAprox(traerRecetas.get(i).getTiempoAprox())
        .setTitulo(traerRecetas.get(i).getTitulo())
        .addAllIngredientes(ingredienteData)
        .addAllPasos(pasoData)
        .addAllFotos(fotoData)
        .build();
        
        recetaGrpcList.add(response);


       }
       getRecetas recetasLista= getRecetas.newBuilder()
       .addAllRecetas(recetaGrpcList)
       .build();
       responseObserver.onNext(recetasLista);
       responseObserver.onCompleted();
    }catch(ServerException e){
        responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
    }

    }

    @Override
    public void traerRecetas(RecetaRequestFilter request, StreamObserver<getRecetas> responseObserver) {
        try{
       List <RecetaResponseDto> traerRecetas=recetaService.traerRecetas(request.getTitulo(),request.getCategoria(),request.getPage(),
       request.getSize(),request.getOrderBy(),request.getSortBy());
        List <RecetaResponse>recetaGrpcList=new ArrayList<>();
        
       for(int i=0;i<traerRecetas.size();i++){
        List<Ingrediente>ingredienteData=new ArrayList<>();
        List<Paso>pasoData=new ArrayList<>();
        List<Foto>fotoData=new ArrayList<>();
        for(int j=0; j<traerRecetas.get(i).getIngredientes().size();j++){
            Ingrediente ingredienteGrpcAdd=Ingrediente.newBuilder()
            .setCantidad(traerRecetas.get(i).getIngredientes().get(j).getCantidad())
            .setNombre(traerRecetas.get(i).getIngredientes().get(j).getNombre())
            .build();
            ingredienteData.add(ingredienteGrpcAdd);
        }
        for(int y=0; y<traerRecetas.get(i).getPasos().size();y++){
            Paso pasoGrpcAdd=Paso.newBuilder()
            .setDescripcion(traerRecetas.get(i).getPasos().get(y).getDescripcion())
            .setNumero(traerRecetas.get(i).getPasos().get(y).getNumero())
            .build();
            pasoData.add(pasoGrpcAdd);
        }
        for(int z=0;z<traerRecetas.get(i).getFotos().size();z++){
            Foto fotoGrpcAdd=Foto.newBuilder()
            .setUrl(traerRecetas.get(i).getFotos().get(z).getUrl())
            .build();
            fotoData.add(fotoGrpcAdd);

        }
        RecetaResponse  response=RecetaResponse.newBuilder()
        .setId(traerRecetas.get(i).getId())
        .setCategoria(traerRecetas.get(i).getCategoria())
        .setDescripcion(traerRecetas.get(i).getDescripcion())
        .setTiempoAprox(traerRecetas.get(i).getTiempoAprox())
        .setTitulo(traerRecetas.get(i).getTitulo())
        .addAllIngredientes(ingredienteData)
        .addAllPasos(pasoData)
        .addAllFotos(fotoData)
        .build();
        
        recetaGrpcList.add(response);


       }
       getRecetas recetasLista= getRecetas.newBuilder()
       .addAllRecetas(recetaGrpcList)
       .build();
       responseObserver.onNext(recetasLista);
       responseObserver.onCompleted();
    }catch(ServerException e ){
        responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
    }

    }

    @Override
    public void traerIngredientes(RecetaRequestById request, StreamObserver<ListaIngredientes> responseObserver) {
       try{ 
        List<com.unla.chefEnCasa.server.entity.Ingrediente> traerIngredientes= recetaService.traerIngredientes(request.getIdReceta());
        List<Ingrediente>ingredienteGrpcList=new ArrayList<>();
        for(int i=0;i<traerIngredientes.size();i++){
             Ingrediente ingredienteGrpcAdd=Ingrediente.newBuilder()
            .setCantidad(traerIngredientes.get(i).getCantidad())
            .setNombre(traerIngredientes.get(i).getNombre())
            .build();
            ingredienteGrpcList.add(ingredienteGrpcAdd);
        }
        ListaIngredientes list= ListaIngredientes.newBuilder()
        .addAllIngrediente(ingredienteGrpcList)
        .build();
        
        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }catch(ServerException e){
        responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
    }

        
    }

    @Override
    public void traerRecetasFavoritas(UsuarioRequestByUserId request, StreamObserver<getRecetas> responseObserver) {
      try{  
     List <RecetaResponseDto> traerRecetas=recetaService.traerFavoritos(request.getId());
         List <RecetaResponse>recetaGrpcList=new ArrayList<>();
        
       for(int i=0;i<traerRecetas.size();i++){
        List<Ingrediente>ingredienteData=new ArrayList<>();
        List<Paso>pasoData=new ArrayList<>();
        List<Foto>fotoData=new ArrayList<>();
        for(int j=0; j<traerRecetas.get(i).getIngredientes().size();j++){
            Ingrediente ingredienteGrpcAdd=Ingrediente.newBuilder()
            .setCantidad(traerRecetas.get(i).getIngredientes().get(j).getCantidad())
            .setNombre(traerRecetas.get(i).getIngredientes().get(j).getNombre())
            .build();
            ingredienteData.add(ingredienteGrpcAdd);
        }
        for(int y=0; y<traerRecetas.get(i).getPasos().size();y++){
            Paso pasoGrpcAdd=Paso.newBuilder()
            .setDescripcion(traerRecetas.get(i).getPasos().get(y).getDescripcion())
            .setNumero(traerRecetas.get(i).getPasos().get(y).getNumero())
            .build();
            pasoData.add(pasoGrpcAdd);
        }
        for(int z=0;z<traerRecetas.get(i).getFotos().size();z++){
            Foto fotoGrpcAdd=Foto.newBuilder()
            .setUrl(traerRecetas.get(i).getFotos().get(z).getUrl())
            .build();
            fotoData.add(fotoGrpcAdd);

        }
        RecetaResponse  response=RecetaResponse.newBuilder()
        .setId(traerRecetas.get(i).getId())
        .setCategoria(traerRecetas.get(i).getCategoria())
        .setDescripcion(traerRecetas.get(i).getDescripcion())
        .setTiempoAprox(traerRecetas.get(i).getTiempoAprox())
        .setTitulo(traerRecetas.get(i).getTitulo())
        .addAllIngredientes(ingredienteData)
        .addAllPasos(pasoData)
        .addAllFotos(fotoData)
        .build();
        
        recetaGrpcList.add(response);


       }
       getRecetas recetasLista= getRecetas.newBuilder()
       .addAllRecetas(recetaGrpcList)
       .build();
       responseObserver.onNext(recetasLista);
       responseObserver.onCompleted();
    }catch(ServerException e){
        responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
    }
    }

    @Override
    public void traerTodasLasRecetas(Empty2 request, StreamObserver<getRecetas> responseObserver) {
        List <RecetaResponseDto> traerRecetas=recetaService.traerTodasLasRecetas();
         List <RecetaResponse>recetaGrpcList=new ArrayList<>();
        
       for(int i=0;i<traerRecetas.size();i++){
        List<Ingrediente>ingredienteData=new ArrayList<>();
        List<Paso>pasoData=new ArrayList<>();
        List<Foto>fotoData=new ArrayList<>();
        for(int j=0; j<traerRecetas.get(i).getIngredientes().size();j++){
            Ingrediente ingredienteGrpcAdd=Ingrediente.newBuilder()
            .setCantidad(traerRecetas.get(i).getIngredientes().get(j).getCantidad())
            .setNombre(traerRecetas.get(i).getIngredientes().get(j).getNombre())
            .build();
            ingredienteData.add(ingredienteGrpcAdd);
        }
        for(int y=0; y<traerRecetas.get(i).getPasos().size();y++){
            Paso pasoGrpcAdd=Paso.newBuilder()
            .setDescripcion(traerRecetas.get(i).getPasos().get(y).getDescripcion())
            .setNumero(traerRecetas.get(i).getPasos().get(y).getNumero())
            .build();
            pasoData.add(pasoGrpcAdd);
        }
        for(int z=0;z<traerRecetas.get(i).getFotos().size();z++){
            Foto fotoGrpcAdd=Foto.newBuilder()
            .setUrl(traerRecetas.get(i).getFotos().get(z).getUrl())
            .build();
            fotoData.add(fotoGrpcAdd);

        }
        RecetaResponse  response=RecetaResponse.newBuilder()
        .setId(traerRecetas.get(i).getId())
        .setCategoria(traerRecetas.get(i).getCategoria())
        .setDescripcion(traerRecetas.get(i).getDescripcion())
        .setTiempoAprox(traerRecetas.get(i).getTiempoAprox())
        .setTitulo(traerRecetas.get(i).getTitulo())
        .addAllIngredientes(ingredienteData)
        .addAllPasos(pasoData)
        .addAllFotos(fotoData)
        .build();
        
        recetaGrpcList.add(response);


       }
       getRecetas recetasLista= getRecetas.newBuilder()
       .addAllRecetas(recetaGrpcList)
       .build();
       responseObserver.onNext(recetasLista);
       responseObserver.onCompleted();
    }
    
}


