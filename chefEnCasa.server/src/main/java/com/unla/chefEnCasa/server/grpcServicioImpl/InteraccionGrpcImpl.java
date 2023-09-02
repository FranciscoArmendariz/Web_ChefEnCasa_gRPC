package com.unla.chefEnCasa.server.grpcServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.grpc.IdSeguirUsuario;
import com.unla.chefEnCasa.grpc.StringSeguido;
import com.unla.chefEnCasa.grpc.favorito;
import com.unla.chefEnCasa.grpc.interaccionGrpc.interaccionImplBase;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.service.InteraccionService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class InteraccionGrpcImpl extends interaccionImplBase {

    @Autowired
    private InteraccionService interaccionService;

    @Override
    public void seguirUsuario(IdSeguirUsuario request, StreamObserver<StringSeguido> responseObserver) {
        try {
             String seguido = interaccionService.seguirUsuario(request.getIdSeguidor(), request.getIdSeguir());
            StringSeguido response = StringSeguido.newBuilder()
                    .setSeguido(seguido)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

    @Override
    public void dejarDeSeguirUsuario(IdSeguirUsuario request, StreamObserver<StringSeguido> responseObserver) {
        try {
            String dejarSeguir = interaccionService.dejarDeSeguirUsuario(request.getIdSeguidor(), request.getIdSeguir());
            StringSeguido response = StringSeguido.newBuilder()
                    .setSeguido(dejarSeguir)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }
    @Override
    public void agregarFavorito(favorito request, StreamObserver<StringSeguido> responseObserver) {  
        try {
            String agregarFavorito = interaccionService.agregarFavorito(request.getIdUsuario(), request.getIdReceta());
            StringSeguido response = StringSeguido.newBuilder()
                    .setSeguido(agregarFavorito)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

    @Override
    public void removerFavorito(favorito request, StreamObserver<StringSeguido> responseObserver) {
        try {
            String removerFavorito = interaccionService.removerFavorito(request.getIdUsuario(), request.getIdReceta());
            StringSeguido response = StringSeguido.newBuilder()
                    .setSeguido(removerFavorito)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

}
