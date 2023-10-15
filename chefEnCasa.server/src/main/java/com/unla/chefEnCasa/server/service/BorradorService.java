package com.unla.chefEnCasa.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.chefEnCasa.server.entity.Borrador;
import com.unla.chefEnCasa.server.entity.Foto;
import com.unla.chefEnCasa.server.entity.FotoIncompleta;
import com.unla.chefEnCasa.server.entity.Ingrediente;
import com.unla.chefEnCasa.server.entity.IngredienteIncompleto;
import com.unla.chefEnCasa.server.entity.Paso;
import com.unla.chefEnCasa.server.entity.PasoIncompleto;
import com.unla.chefEnCasa.server.entity.Receta;
import com.unla.chefEnCasa.server.entity.RecetaIncompleta;
import com.unla.chefEnCasa.server.entity.Usuario;
import com.unla.chefEnCasa.server.repository.BorradorRepository;
import com.unla.chefEnCasa.server.repository.RecetaIncompletaRepository;
import com.unla.chefEnCasa.server.repository.RecetaRepository;
import com.unla.chefEnCasa.server.repository.UsuarioRepository;

@Service
public class BorradorService {

    @Autowired
    private BorradorRepository borradorRepository;

    @Autowired
    private RecetaIncompletaRepository recetaIncompletaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecetaRepository recetasRepository;

    public boolean crearBorrador(List<RecetaIncompleta> borrador, long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(null);

        Borrador borr = new Borrador();
        borr.setIdUsuario(usuario.getId());
        borradorRepository.save(borr);

        List<RecetaIncompleta> recetas = new ArrayList<>();
        for (RecetaIncompleta r : borrador) {
            RecetaIncompleta nueva = new RecetaIncompleta();
            nueva.setTitulo(r.getTitulo());
            nueva.setDescripcion(r.getDescripcion());
            nueva.setCategoria(r.getCategoria());
            nueva.setTiempoAprox(r.getTiempoAprox());
            nueva.setBorrador(borr);
            recetas.add(nueva);
            recetaIncompletaRepository.save(nueva);
        }
        return true;
    }

    public List<Borrador> traerBorradoresPorUsuario(long idUsuario) { // SOLO LOS BORRADORES SIN LAS LISTAS DE RECETAS
                                                                      // INCOMPLETAS
        return borradorRepository.findByIdUsuario(idUsuario);
    }

    public List<RecetaIncompleta> traerBorrador(long idBorrador) { // EL BORRADOR CON SUS RECETAS INCOMPLETAS
        return recetaIncompletaRepository.findAllByBorradorId(idBorrador);
    }

    public boolean editarBorrador(long idBorrador, List<RecetaIncompleta> recetasEditadas) {

        List<RecetaIncompleta> recetasActuales = traerBorrador(idBorrador);

        for (int i = 0; i < recetasActuales.size(); i++) {
            RecetaIncompleta r = recetasActuales.get(i);
            RecetaIncompleta r2 = recetasEditadas.get(i);

            r.setTitulo(r2.getTitulo());
            r.setDescripcion(r2.getDescripcion());
            r.setCategoria(r2.getCategoria());
            r.setTiempoAprox(r2.getTiempoAprox());

            recetaIncompletaRepository.deleteIngredientesByRecetaId(r);
            recetaIncompletaRepository.deleteFotosByRecetaId(r);
            recetaIncompletaRepository.deletePasosByRecetaId(r);

            for (IngredienteIncompleto t : r2.getIngredientes()) {
                IngredienteIncompleto aux = new IngredienteIncompleto();
                aux.setNombre(t.getNombre());
                aux.setCantidad(t.getCantidad());
                aux.setRecetaIncompleta(r);
                r.getIngredientes().add(aux);
            }
            for (FotoIncompleta f : r2.getFotos()) {
                FotoIncompleta foto = new FotoIncompleta();
                foto.setUrl(f.getUrl());
                foto.setRecetaIncompleta(r);
                r.getFotos().add(foto);
            }
            for (PasoIncompleto g : r2.getPasos()) {
                PasoIncompleto aux = new PasoIncompleto();
                aux.setDescripcion(g.getDescripcion());
                aux.setNumero(g.getNumero());
                aux.setRecetaIncompleta(r);
                r.getPasos().add(aux);
            }
            recetaIncompletaRepository.save(r);
        }

        return true;
    }

    public boolean crearRecetas(long idBorrador, List<RecetaIncompleta> recetas) {

        // PRIMERO BORRO EL BORRADOR COMPLETO - NO HAY VALIDACIONES - SE SUPONE QUE
        // LLEGA BIEN
        Borrador borrador = borradorRepository.findById(idBorrador).orElseThrow(null);
        Usuario usuario = usuarioRepository.findById(borrador.getIdUsuario()).orElseThrow(null);

        for (RecetaIncompleta r : recetas) {

            Receta nueva = new Receta();
            nueva.setTitulo(r.getTitulo());
            nueva.setDescripcion(r.getDescripcion());
            nueva.setCategoria(r.getCategoria());
            nueva.setTiempoAprox(r.getTiempoAprox());
            nueva.setActiva(true);

            List<Ingrediente> ingredientes = new ArrayList<>();
            for (IngredienteIncompleto i : r.getIngredientes()) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setNombre(i.getNombre());
                ingrediente.setCantidad(i.getCantidad());
                ingrediente.setReceta(nueva);
                ingredientes.add(ingrediente);
            }
            nueva.setIngredientes(ingredientes);

            List<Foto> fotos = new ArrayList<>();
            for (FotoIncompleta f : r.getFotos()) {
                Foto foto = new Foto();
                foto.setUrl(f.getUrl());
                foto.setReceta(nueva);
                fotos.add(foto);
            }
            nueva.setFotos(fotos);

            List<Paso> pasos = new ArrayList<>();
            for (PasoIncompleto p : r.getPasos()) {
                Paso paso = new Paso();
                paso.setNumero(p.getNumero());
                paso.setDescripcion(p.getDescripcion());
                paso.setReceta(nueva);
                pasos.add(paso);
            }
            nueva.setPasos(pasos);
            recetasRepository.save(nueva);
            usuario.getRecetasCreadas().add(nueva);

        }

         recetaIncompletaRepository.deleteByBorrador(borrador);
         borradorRepository.deleteById(borrador.getId());

        return true;
    }

}
