import api from "@/api";

const RUTA = "";

const recetaApi = {
  traerRecetasConFiltros: (filtros) =>
    api.post(`${RUTA}/TraerRecetas`, filtros),
  traerRecetas: () => api.post(`${RUTA}/TraerTodasLasRecetas`),
  traerRecetasNuevas: () => api.post(`${RUTA}/TraerRecetasNuevas`),
  crearReceta: (value) => api.post(`${RUTA}/CrearReceta`, value),
  editarReceta: (value) =>
    console.log("value", value) || api.post(`${RUTA}/EditarReceta`, value),
  traerRecetasPorUsuario: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasPorId`, { id: idUsuario }),
  traerRecetasFavoritas: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasFavoritas`, { id: idUsuario }),
  traerIngredientes: () => api.post(`${RUTA}/TraerTodosLosIngredientes`, {}),
  traerRecetaPorId: (id) =>
    api.post(`${RUTA}/TraerRecetasPorIdReceta`, { idReceta: id }),
  verUltimasRecetas: () => api.post(`${RUTA}/VerUltimasRecetas`),
  nuevoComentario: ({ idRedactor, idReceta, comentario, esAutor }) =>
    api.post(`${RUTA}/NuevoComentario`, {
      idRedactor,
      idReceta,
      comentario,
      esAutor,
    }),
  calificarReceta: (idReceta, puntaje) =>
    api.post(`${RUTA}/calificarReceta`, {
      idReceta,
      puntaje,
      calificacion: true,
    }),
  traerRecetarios: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetariosPorUsuario`, { idUsuario }),
  traerRecetario: (idRecetario) =>
    api.post(`${RUTA}/TraerRecetarioConRecetas`, { idRecetario }),
  crearRecetario: ({ idAutor, nombre }) =>
    api.post(`${RUTA}/CrearRecetario`, { idAutor, nombre }),
  borrarRecetario: (idRecetario) =>
    api.post(`${RUTA}/BorrarRecetario`, { idRecetario }),
  agregarRecetaRecetario: ({ idRecetario, idReceta }) =>
    api.post(`${RUTA}/AgregarRecetaRecetario`, { idRecetario, idReceta }),
  removerRecetaRecetario: ({ idRecetario, idReceta }) =>
    api.post(`${RUTA}/RemoverRecetaRecetario`, { idRecetario, idReceta }),
};

export default recetaApi;
