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

	public RecetaResponse crearReceta(RecetaRequest receta, long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ServerException("Usuario no encontrado", HttpStatus.NOT_FOUND));
		Receta newReceta = new Receta();
		newReceta.setTitulo(receta.getTitulo());
		newReceta.setDescripcion(receta.getDescripcion());
		newReceta.setCategoria(receta.getCategoria());
		newReceta.setTiempoAprox(receta.getTiempoAprox());

		List<Ingrediente> ingredientes = new ArrayList<>();
		for (int i = 0; i < receta.getIngredientes().size(); i++) {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setNombre(receta.getIngredientes().get(i).getNombre());
			ingrediente.setCantidad(receta.getIngredientes().get(i).getCantidad());
			ingrediente.setReceta(newReceta);
			ingredientes.add(ingrediente);

		}
		newReceta.setIngredientes(ingredientes);

		List<Paso> pasos = new ArrayList<>();
		for (int i = 0; i < receta.getPasos().size(); i++) {
			Paso paso = new Paso();
			paso.setNumero(receta.getPasos().get(i).getNumero());
			paso.setDescripcion(receta.getPasos().get(i).getDescripcion());
			paso.setReceta(newReceta);
			pasos.add(paso);
		}
		newReceta.setPasos(pasos);
		usuario.getRecetasCreadas().add(newReceta);

		usuarioRepository.save(usuario);
		recetaRepository.save(newReceta);

		RecetaResponse response = modelMapper.map(newReceta, RecetaResponse.class);
		return response;
	}
	
	public List<RecetaResponse> traerRecetas(String titulo, String categoria, int page, int size, String orderBy, String sortBy){
		try {
			if(page < 1) page = 1; if(size < 1) size = 999999;
			Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orderBy.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy.toLowerCase()));
			Page<Receta> pageTipo;
			if(!titulo.isBlank()) {
				pageTipo = recetaRepository.findByTituloContaining(titulo, pageable);
			}else {
				pageTipo = recetaRepository.findByCategoriaContaining(categoria, pageable);
			}
			List<RecetaResponse> response = new ArrayList<>();
			for(Receta r : pageTipo.getContent()) {
				response.add(modelMapper.map(r, RecetaResponse.class));
			}
			return response;
		}catch(Exception e) {
			throw new ServerException("error al listas las recetas", HttpStatus.BAD_REQUEST);
		}
	}

}
