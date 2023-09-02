package com.unla.chefEnCasa.server.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.RecetaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class InteraccionService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Transactional
	public String seguirUsuario(long idSeguidor, long idSeguir) {
		Usuario seguidor = usuarioRepository.findById(idSeguidor)
				.orElseThrow(() -> new ServerException("no existe un usuario con id: "+idSeguidor, HttpStatus.NOT_FOUND));
		Usuario seguir = usuarioRepository.findById(idSeguir)
				.orElseThrow(() -> new ServerException("no existe un usuario a seguir con id: "+idSeguir, HttpStatus.NOT_FOUND));
		List<Usuario> seguidos = seguidor.getUsuariosSeguidos();
		if(seguidos.contains(seguir)) {
			throw new ServerException("No podes seguir a un usuario que ya seguis", HttpStatus.BAD_REQUEST);
		}else{
		seguidos.add(seguir);
		seguidor.setUsuariosSeguidos(seguidos);
		usuarioRepository.save(seguidor);
		return "usuario seguido correctamente";
		}
	}
	@Transactional
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
	//REVISAR MAÑANA 
	public String agregarFavorito(long idUsuario, long idReceta) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ServerException("no existe un usuario con id: "+idUsuario, HttpStatus.NOT_FOUND));
		Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new ServerException("no existe una receta con id: "+idReceta, HttpStatus.NOT_FOUND));
		Set<Receta> recetas = usuario.getRecetasFavoritas();
			if(recetas.contains(receta)) {
				throw new ServerException("ya tenes esa receta en tu lista de favoritos", HttpStatus.BAD_REQUEST);
			}
		recetas.add(receta);
		usuario.setRecetasFavoritas(recetas);
		usuarioRepository.save(usuario);
		return "receta añadida a favoritos correctamente";
	}
	public String removerFavorito(long idUsuario, long idReceta) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ServerException("no existe un usuario con id: "+idUsuario, HttpStatus.NOT_FOUND));
		Receta receta = recetaRepository.findById(idReceta).orElseThrow(() -> new ServerException("no existe una receta con id: "+idReceta, HttpStatus.NOT_FOUND));
		Set<Receta> recetas = usuario.getRecetasFavoritas();
				if(!recetas.contains(receta)) {
				throw new ServerException("no tenes esa receta en tu lista de favoritos", HttpStatus.BAD_REQUEST);
			}
		
		recetas.remove(receta);
		usuarioRepository.save(usuario);
		return "receta removida de favoritos correctamente";
	}
	
}
