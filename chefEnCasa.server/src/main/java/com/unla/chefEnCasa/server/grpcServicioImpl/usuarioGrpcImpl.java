package com.unla.chefEnCasa.server.grpcServicioImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.server.dto.LoginDto;
import com.unla.chefEnCasa.server.dto.UsuarioRequestDto;
import com.unla.chefEnCasa.server.dto.UsuarioResponseDto;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.grpc.Empty;
import com.unla.chefEnCasa.grpc.LoginUsuario;
import com.unla.chefEnCasa.grpc.UsuarioRequest;
import com.unla.chefEnCasa.grpc.UsuarioRequestById;
import com.unla.chefEnCasa.grpc.UsuarioResponse;
import com.unla.chefEnCasa.grpc.getCreado;
import com.unla.chefEnCasa.grpc.getUsuarios;
import com.unla.chefEnCasa.grpc.usuarioGrpc.usuarioImplBase;
import com.unla.chefEnCasa.server.service.UsuarioService;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class usuarioGrpcImpl extends usuarioImplBase {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void crearUsuario(UsuarioRequest request, StreamObserver<getCreado> responseObserver) {
        UsuarioRequestDto usuario = new UsuarioRequestDto();
        usuario.setNombre(request.getNombre());
        usuario.setUsuario(request.getUsuario());
        usuario.setEmail(request.getEmail());
        usuario.setClave(request.getClave());
        usuario.setRol(request.getRol());
        boolean usuarioResponseDto = usuarioService.crearUsuario(usuario);
        getCreado response = getCreado.newBuilder()
                .setCreado(usuarioResponseDto)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginUsuario request, StreamObserver<UsuarioResponse> responseObserver) {
        LoginDto login = new LoginDto();
        login.setUsuario(request.getUsuario());
        login.setClave(request.getClave());
        try {
            com.unla.chefEnCasa.server.entity.Usuario usuario = usuarioService.login(login);
            UsuarioResponse response = UsuarioResponse.newBuilder()
                    .setId(usuario.getId())
                    .setNombre(usuario.getNombre())
                    .setUsuario(usuario.getUsuario())
                    .setEmail(usuario.getEmail())
                    .setRol(usuario.getRol())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ServerException e) {
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

    @Override
    public void traerUsuarios(Empty request, StreamObserver<getUsuarios> responseObserver) {
        List<UsuarioResponseDto> traerUsuarios = usuarioService.traerUsuarios();
        List<UsuarioResponse> usuarios = new ArrayList<>();
        for (int i = 0; i < traerUsuarios.size(); i++) {
            UsuarioResponse usuarioResponse = UsuarioResponse.newBuilder()
                    .setId(traerUsuarios.get(i).getId())
                    .setNombre(traerUsuarios.get(i).getNombre())
                    .setUsuario(traerUsuarios.get(i).getUsuario())
                    .setEmail(traerUsuarios.get(i).getEmail())
                    .setRol(traerUsuarios.get(i).getRol())
                    .build();
            usuarios.add(usuarioResponse);
        }
        getUsuarios response = getUsuarios.newBuilder()
                .addAllUsuarios(usuarios)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void traerUsuariosSeguidos(UsuarioRequestById request, StreamObserver<getUsuarios> responseObserver) {
       try{
        List<UsuarioResponseDto> traerUsuarios = usuarioService.traerUsuariosSeguidos(request.getId());
        List<UsuarioResponse> usuarios = new ArrayList<>();
        for (int i = 0; i < traerUsuarios.size(); i++) {
            UsuarioResponse usuarioResponse = UsuarioResponse.newBuilder()
                    .setId(traerUsuarios.get(i).getId())
                    .setNombre(traerUsuarios.get(i).getNombre())
                    .setUsuario(traerUsuarios.get(i).getUsuario())
                    .setEmail(traerUsuarios.get(i).getEmail())
                    .setRol(traerUsuarios.get(i).getRol())
                    .build();
            usuarios.add(usuarioResponse);
        }
        getUsuarios response = getUsuarios.newBuilder()
                .addAllUsuarios(usuarios)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        } catch(ServerException e){
            responseObserver.onError(Status.UNKNOWN.withDescription(e.getMensaje()).asRuntimeException());
        }
    }

}
