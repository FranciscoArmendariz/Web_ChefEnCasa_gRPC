package com.unla.chefEnCasa.server.grpcServicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unla.chefEnCasa.server.dto.LoginDto;
import com.unla.chefEnCasa.server.dto.UsuarioRequestDto;
import com.unla.chefEnCasa.server.dto.UsuarioResponseDto;
import com.unla.chefEnCasa.grpc.Empty;
import com.unla.chefEnCasa.grpc.LoginUsuario;
import com.unla.chefEnCasa.grpc.Usuario;
import com.unla.chefEnCasa.grpc.UsuarioRequest;
import com.unla.chefEnCasa.grpc.UsuarioRequestById;
import com.unla.chefEnCasa.grpc.UsuarioResponse;
import com.unla.chefEnCasa.grpc.usuarioGrpc.usuarioImplBase;
import com.unla.chefEnCasa.server.service.UsuarioService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UsuarioGrpcImpl extends usuarioImplBase {

	@Autowired
    private UsuarioService usuarioService;

    @Override
    public void crearUsuario(UsuarioRequest request, StreamObserver<UsuarioResponse> responseObserver) {
        UsuarioRequestDto usuario = new UsuarioRequestDto();
        usuario.setNombre(request.getNombre());
        usuario.setUsuario(request.getUsuario());
        usuario.setEmail(request.getEmail());
        usuario.setClave(request.getClave());
        usuario.setRol(request.getRol());

        UsuarioResponseDto usuarioResponseDto = usuarioService.crearUsuario(usuario);
        UsuarioResponse response = UsuarioResponse.newBuilder()
                .setNombre(usuarioResponseDto.getNombre())
                .setUsuario(usuarioResponseDto.getUsuario())
                .setEmail(usuarioResponseDto.getEmail())
                .setRol(usuarioResponseDto.getRol())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void login(LoginUsuario request, StreamObserver<Usuario> responseObserver) {
        LoginDto login = new LoginDto();
        login.setUsuario(request.getUsuario());
        login.setClave(request.getClave());

        com.unla.chefEnCasa.server.entity.Usuario usuario = usuarioService.login(login);
        Usuario response = Usuario.newBuilder()
                .setNombre(usuario.getNombre())
                .setUsuario(usuario.getUsuario())
                .setEmail(usuario.getEmail())
                .setClave(usuario.getClave())
                .setRol(usuario.getRol())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void traerUsuarios(Empty request, StreamObserver<UsuarioResponse> responseObserver) {
        List<UsuarioResponseDto> usuarios = usuarioService.traerUsuarios();
        for(int i=0;i<usuarios.size();i++){
            UsuarioResponse response = UsuarioResponse.newBuilder()
            .setNombre(usuarios.get(i).getNombre())
            .setUsuario(usuarios.get(i).getUsuario())
            .setEmail(usuarios.get(i).getEmail())
            .setRol(usuarios.get(i).getRol())
            .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void traerUsuariosSeguidos(UsuarioRequestById request, StreamObserver<UsuarioResponse> responseObserver) {
        List<UsuarioResponseDto> usuarios = usuarioService.traerUsuariosSeguidos(request.getId());

        for(int i=0;i<usuarios.size();i++){
            UsuarioResponse response = UsuarioResponse.newBuilder()
            .setNombre(usuarios.get(i).getNombre())
            .setUsuario(usuarios.get(i).getUsuario())
            .setEmail(usuarios.get(i).getEmail())
            .setRol(usuarios.get(i).getRol())
            .build();
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
    
}
