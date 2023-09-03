package com.unla.chefEnCasa.server.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.chefEnCasa.server.dto.RecetaRequestDto;
import com.unla.chefEnCasa.server.dto.RecetaResponseDto;
import com.unla.chefEnCasa.server.entity.Foto;
import com.unla.chefEnCasa.server.entity.Ingrediente;
import com.unla.chefEnCasa.server.entity.Paso;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.exceptions.ServerException;
import com.unla.chefEnCasa.server.repository.RecetaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class RecetaService {

	@Autowired
	private RecetaRepository recetaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public boolean crearReceta(RecetaRequestDto request, long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));
		Receta newReceta = new Receta();
		newReceta.setTitulo(request.getTitulo());
		newReceta.setDescripcion(request.getDescripcion());
		newReceta.setCategoria(request.getCategoria());
		newReceta.setTiempoAprox(request.getTiempoAprox());

		List<Foto> fotos = new ArrayList<>();
		for(Foto f : request.getFotos()) {
			Foto foto = new Foto();
			foto.setUrl(f.getUrl());
			foto.setReceta(newReceta);
			fotos.add(foto);
		}
		newReceta.setFotos(fotos);

		List<Ingrediente> ingredientes = new ArrayList<>();
		for (Ingrediente i : request.getIngredientes()) {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setNombre(i.getNombre());
			ingrediente.setCantidad(i.getCantidad());
			ingrediente.setReceta(newReceta);
			ingredientes.add(ingrediente);
		}
		newReceta.setIngredientes(ingredientes);

		List<Paso> pasos = new ArrayList<>();
		for (Paso p : request.getPasos()) {
			Paso paso = new Paso();
			paso.setNumero(p.getNumero());
			paso.setDescripcion(p.getDescripcion());
			paso.setReceta(newReceta);
			pasos.add(paso);
		}
		newReceta.setPasos(pasos);
		usuario.getRecetasCreadas().add(newReceta);
		try {
			recetaRepository.save(newReceta);
			usuarioRepository.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Transactional
	public boolean editarReceta(RecetaRequestDto request, long id) {
		Receta editReceta = recetaRepository.findById(id)
				.orElseThrow(() -> new ServerException("Receta no encontrada", HttpStatus.NOT_FOUND));
		editReceta.setTitulo(request.getTitulo());
		editReceta.setDescripcion(request.getDescripcion());
		editReceta.setCategoria(request.getCategoria());
		editReceta.setTiempoAprox(request.getTiempoAprox());

		List<Foto> fotos = editReceta.getFotos();
		for(int i=0; i<request.getFotos().size();i++){
			Foto foto = fotos.get(i);
			foto.setUrl(request.getFotos().get(i).getUrl());
			foto.setReceta(editReceta);
			fotos.add(foto);
		}
		editReceta.setFotos(fotos);

		List<Ingrediente> ingredientes = editReceta.getIngredientes();
		for (int i = 0; i < request.getIngredientes().size(); i++) {
			Ingrediente ingrediente = ingredientes.get(i);
			ingrediente.setNombre(request.getIngredientes().get(i).getNombre());
			ingrediente.setCantidad(request.getIngredientes().get(i).getCantidad());
			ingrediente.setReceta(editReceta);
			ingredientes.add(ingrediente);
		}
		editReceta.setIngredientes(ingredientes);

		List<Paso> pasos = editReceta.getPasos();
		for (int i = 0; i < request.getPasos().size(); i++) {
			Paso paso = pasos.get(i);
			paso.setNumero(request.getPasos().get(i).getNumero());
			paso.setDescripcion(request.getPasos().get(i).getDescripcion());
			paso.setReceta(editReceta);
			pasos.add(paso);
		}
		editReceta.setPasos(pasos);
		try {
			recetaRepository.save(editReceta);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Transactional
	public List<RecetaResponseDto> traerRecetas(String titulo, String categoria, int page, int size, String orderBy,
			String sortBy) {
		try {
			if (page < 1)
				page = 1;
			if (size < 1)
				size = 999999;
			Pageable pageable = PageRequest.of(page - 1, size, Sort.by(
					orderBy.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy.toLowerCase()));
			Page<Receta> pageTipo;
			if (!titulo.isBlank()) {
				pageTipo = recetaRepository.findByTituloContaining(titulo, pageable);
			} else {
				pageTipo = recetaRepository.findByCategoriaContaining(categoria, pageable);
			}
			List<RecetaResponseDto> response = new ArrayList<>();
			for (Receta r : pageTipo.getContent()) {
				response.add(modelMapper.map(r, RecetaResponseDto.class));
			}
			return response;
		} catch (Exception e) {
			throw new ServerException("error al listas las recetas", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	public List<RecetaResponseDto> traerTodasLasRecetas(){
		List<Receta> recetas = recetaRepository.findAll();
		return recetas.stream().map(receta -> modelMapper.map(receta, RecetaResponseDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public List<RecetaResponseDto> traerRecetasPorId(long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));

		List<RecetaResponseDto> response = new ArrayList<>();
		for (Receta r : usuario.getRecetasCreadas()) {
			response.add(modelMapper.map(r, RecetaResponseDto.class));
		}
		return response;
	}
	@Transactional
	public List<RecetaResponseDto> traerFavoritos(long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(
						() -> new ServerException("no existe un usuario con id: " + idUsuario, HttpStatus.NOT_FOUND));

		List<RecetaResponseDto> response = new ArrayList<>();
		for (Receta r : usuario.getRecetasFavoritas()) {
			response.add(modelMapper.map(r, RecetaResponseDto.class));
		}
		return response;
	}
	@Transactional
	public List<Ingrediente> traerIngredientes(long idReceta){
		Receta receta=recetaRepository.findById(idReceta)
		.orElseThrow(() -> new ServerException("no existe la receta con id: "+idReceta, HttpStatus.NOT_FOUND));
		List <Ingrediente> response=new ArrayList<>();
		for(Ingrediente r:receta.getIngredientes() ){
			response.add(modelMapper.map(r, Ingrediente.class));
		}
		return response;
	}
	 @Transactional
	public RecetaResponseDto traerRecetasPorIdReceta(long idReceta){
		Receta receta= recetaRepository.findById(idReceta)
		.orElseThrow(() -> new ServerException("no existe la receta con id: "+idReceta, HttpStatus.NOT_FOUND));
		return modelMapper.map(receta, RecetaResponseDto.class);
				

		

	}

}
