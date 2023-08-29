package com.unla.chefEnCasa.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class InteraccionService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public String seguirUsuario(long idSeguidor, long idSeguir) {
		Usuario seguidor = usuarioRepository.findById(idSeguidor)
				.orElseThrow(() -> new ServerException("no existe un usuario con id: "+idSeguidor, HttpStatus.NOT_FOUND));
		Usuario seguir = usuarioRepository.findById(idSeguir)
				.orElseThrow(() -> new ServerException("no existe un usuario a seguir con id: "+idSeguir, HttpStatus.NOT_FOUND));
		List<Usuario> seguidos = seguidor.getUsuariosSeguidos();
		if(seguidos.contains(seguir)) {
			throw new ServerException("Ya seguis a ese usuario", HttpStatus.BAD_REQUEST);
		}
		seguidos.add(seguir);
		seguidor.setUsuariosSeguidos(seguidos);
		usuarioRepository.save(seguidor);
		return "usuario seguido correctamente";
	}
	
	public String dejarDeSeguirUsuario(long idSeguidor, long idSeguido) {
		Usuario seguidor = usuarioRepository.findById(idSeguidor)
				.orElseThrow(() -> new ServerException("no existe un usuario con id: "+idSeguidor, HttpStatus.NOT_FOUND));
		Usuario seguido = usuarioRepository.findById(idSeguido)
				.orElseThrow(() -> new ServerException("no existe usuario con id: "+idSeguido, HttpStatus.NOT_FOUND));
		List<Usuario> seguidos = seguidor.getUsuariosSeguidos();
		if(!seguidos.contains(seguido)) {
			throw new ServerException("No podes dejar de seguir a un usuario con no seguis", HttpStatus.BAD_REQUEST);
		}
		seguidos.remove(seguido);
		usuarioRepository.save(seguidor);
		return "dejaste de seguir al usuario correctamente";
	}

}
