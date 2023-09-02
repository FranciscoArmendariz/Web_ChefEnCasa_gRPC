package com.unla.chefEnCasa.server.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.dto.LoginDto;
import com.unla.chefEnCasa.server.dto.UsuarioRequestDto;
import com.unla.chefEnCasa.server.dto.UsuarioResponseDto;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public boolean crearUsuario(UsuarioRequestDto request) {
		Usuario usuario = modelMapper.map(request, Usuario.class);
		try{
			usuarioRepository.save(usuario);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public List<UsuarioResponseDto> traerUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(usuario -> modelMapper.map(usuario, UsuarioResponseDto.class))
				.collect(Collectors.toList());
	}

	public Usuario login(LoginDto request) {
		Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario())
				.orElseThrow(() -> new ServerException("Usuario o contraseña incorrecta", HttpStatus.NOT_FOUND));
		if (!usuario.getClave().equals(request.getClave())) {
			throw new ServerException("Usuario o contraseña incorrecta", HttpStatus.BAD_REQUEST);
		}
		return usuario;
	}
	
	public List<UsuarioResponseDto> traerUsuariosSeguidos(long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));
		
		List<UsuarioResponseDto> response = new ArrayList<>();
		for(Usuario u : usuario.getUsuariosSeguidos()) {
			response.add(modelMapper.map(u, UsuarioResponseDto.class));
		}
		return response;
	}

}
