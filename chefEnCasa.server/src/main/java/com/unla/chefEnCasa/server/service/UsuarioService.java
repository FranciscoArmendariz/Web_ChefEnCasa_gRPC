package com.unla.chefEnCasa.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.dto.Login;
import com.unla.chefEnCasa.server.dto.UsuarioRequest;
import com.unla.chefEnCasa.server.dto.UsuarioResponse;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public UsuarioResponse crearUsuario(UsuarioRequest request) {
		Usuario usuario = modelMapper.map(request, Usuario.class);
		usuarioRepository.save(usuario);
		return modelMapper.map(usuario, UsuarioResponse.class);
	}
	public List<UsuarioResponse> traerUsuarios(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(usuario -> modelMapper.map(usuario, UsuarioResponse.class)).collect(Collectors.toList());
	}
	public Usuario login(Login request) {
		Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario()).orElseThrow(() -> new RuntimeException("usuario no encontrado"));
		if(!usuario.getClave().equals(request.getClave())) {
			throw new RuntimeException("usuario o contraseña incorrecta");
		}
		return usuario;
	}

}
