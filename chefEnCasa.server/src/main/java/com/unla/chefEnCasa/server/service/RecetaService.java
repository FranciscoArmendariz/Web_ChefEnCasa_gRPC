package com.unla.chefEnCasa.server.service;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.dto.RecetaRequest;
import com.unla.chefEnCasa.server.dto.RecetaResponse;
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

	public RecetaResponse crearReceta(RecetaRequest request, long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));
		Receta newReceta = new Receta();
		newReceta.setTitulo(request.getTitulo());
		newReceta.setDescripcion(request.getDescripcion());
		newReceta.setCategoria(request.getCategoria());
		newReceta.setTiempoAprox(request.getTiempoAprox());

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

		recetaRepository.save(newReceta);

		RecetaResponse response = modelMapper.map(newReceta, RecetaResponse.class);
		return response;
	}

	public RecetaResponse editarReceta(RecetaRequest request, long id) {
		Receta editReceta = recetaRepository.findById(id)
				.orElseThrow(() -> new ServerException("Receta no encontrada", HttpStatus.NOT_FOUND));
		editReceta.setTitulo(request.getTitulo());
		editReceta.setDescripcion(request.getDescripcion());
		editReceta.setCategoria(request.getCategoria());
		editReceta.setTiempoAprox(request.getTiempoAprox());

		List<Ingrediente> ingredientes = editReceta.getIngredientes();
		for (int i=0; i<request.getIngredientes().size(); i++) {
			Ingrediente ingrediente = ingredientes.get(i);
			ingrediente.setNombre(request.getIngredientes().get(i).getNombre());
			ingrediente.setCantidad(request.getIngredientes().get(i).getCantidad());
			ingrediente.setReceta(editReceta);
			ingredientes.add(ingrediente);
		}
		editReceta.setIngredientes(ingredientes);

		List<Paso> pasos = editReceta.getPasos();
		for (int i=0; i<request.getPasos().size(); i++) {
			Paso paso = pasos.get(i);
			paso.setNumero(request.getPasos().get(i).getNumero());
			paso.setDescripcion(request.getPasos().get(i).getDescripcion());
			paso.setReceta(editReceta);
			pasos.add(paso);
		}
		editReceta.setPasos(pasos);
		recetaRepository.save(editReceta);
		
		RecetaResponse response = modelMapper.map(editReceta, RecetaResponse.class);
		return response;
	}

	public List<RecetaResponse> traerRecetas(String titulo, String categoria, int page, int size, String orderBy,
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
			List<RecetaResponse> response = new ArrayList<>();
			for (Receta r : pageTipo.getContent()) {
				response.add(modelMapper.map(r, RecetaResponse.class));
			}
			return response;
		} catch (Exception e) {
			throw new ServerException("error al listas las recetas", HttpStatus.BAD_REQUEST);
		}
	}
	public List<RecetaResponse> traerRecetasPorId(long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));

		List<RecetaResponse> response = new ArrayList<>();
		for (Receta r : usuario.getRecetasCreadas()) {
			response.add(modelMapper.map(r, RecetaResponse.class));
		}
		return response;
	}

}
