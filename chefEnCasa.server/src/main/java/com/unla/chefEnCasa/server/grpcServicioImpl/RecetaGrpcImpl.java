package com.unla.chefEnCasa.server.grpcServicioImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.grpc.Ingrediente;
import com.unla.chefEnCasa.grpc.Paso;
import com.unla.chefEnCasa.grpc.RecetaRequest;
import com.unla.chefEnCasa.grpc.RecetaRequestFilter;
//import com.unla.chefEnCasa.grpc.RecetaResponse;
import com.unla.chefEnCasa.grpc.RecetaUpdateRequest;
import com.unla.chefEnCasa.grpc.UsuarioRequestByUserId;
import com.unla.chefEnCasa.grpc.getRecetaCreada;
import com.unla.chefEnCasa.grpc.getRecetaEditada;
import com.unla.chefEnCasa.grpc.getRecetas;
import com.unla.chefEnCasa.grpc.recetaGrpc.recetaImplBase;
import com.unla.chefEnCasa.server.dto.RecetaRequestDto;
import com.unla.chefEnCasa.server.exceptions.ServerException;
//import com.unla.chefEnCasa.server.dto.RecetaResponseDto;
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
        // valen
    }

    @Override
    public void traerRecetas(RecetaRequestFilter request, StreamObserver<getRecetas> responseObserver) {
        // VALEN
    }

    @Override
    public void traerRecetasFavoritas(RecetaRequest request, StreamObserver<getRecetas> responseObserver) {
        // VALEN
    }

}
