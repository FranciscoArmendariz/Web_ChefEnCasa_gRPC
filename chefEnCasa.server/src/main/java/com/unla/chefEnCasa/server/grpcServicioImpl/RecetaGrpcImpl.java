package com.unla.chefEnCasa.server.grpcServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.grpc.ListaRecetasRequest;
import com.unla.chefEnCasa.grpc.RecetaRequest;
import com.unla.chefEnCasa.grpc.RecetaResponse;
import com.unla.chefEnCasa.grpc.RecetaUpdateRequest;
import com.unla.chefEnCasa.grpc.UsuarioRequestByUserId;
import com.unla.chefEnCasa.grpc.recetaGrpc.recetaImplBase;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class RecetaGrpcImpl extends recetaImplBase{


    @Autowired
    private RecetaService recetaService;

    @Override
    public void crearReceta(RecetaRequest request, StreamObserver<RecetaResponse> responseObserver) {
        // TODO Auto-generated method stub
        super.crearReceta(request, responseObserver);
    }

    @Override
    public void editarReceta(RecetaUpdateRequest request, StreamObserver<RecetaResponse> responseObserver) {
        // TODO Auto-generated method stub
        super.editarReceta(request, responseObserver);
    }

    @Override
    public void traerFavoritos(RecetaRequest request, StreamObserver<RecetaResponse> responseObserver) {
        // TODO Auto-generated method stub
        super.traerFavoritos(request, responseObserver);
    }

    @Override
    public void traerRecetas(ListaRecetasRequest request, StreamObserver<RecetaResponse> responseObserver) {
        // TODO Auto-generated method stub
        super.traerRecetas(request, responseObserver);
    }

    @Override
    public void traerRecetasPorId(UsuarioRequestByUserId request, StreamObserver<RecetaResponse> responseObserver) {
        // TODO Auto-generated method stub
        super.traerRecetasPorId(request, responseObserver);
    }

    
}
