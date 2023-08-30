package com.unla.chefEnCasa.server.grpcServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.chefservicegrpc.grpc.usuarioGrpc.usuarioImplBase;
import com.unla.chefEnCasa.server.service.UsuarioService;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class usuarioGrpcImpl extends usuarioImplBase {
  @Autowired
  private   UsuarioService serviceUsuario;
  
}
