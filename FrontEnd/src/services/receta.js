import api from "@/api";

const RUTA = "";

const recetaApi = {
  traerRecetasConFiltros: (filtros) =>
    api.post(`${RUTA}/TraerRecetas`, filtros),
  traerRecetas: () => api.post(`${RUTA}/TraerTodasLasRecetas`),
  crearReceta: (value) => api.post(`${RUTA}/CrearReceta`, value),
  editarReceta: (value) => api.post(`${RUTA}/EditarReceta`, value),
  traerRecetasPorUsuario: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasPorId`, idUsuario),
  traerRecetasFavoritas: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasFavoritas`, { id: idUsuario }),
  traerIngredientes: () => api.post(`${RUTA}/TraerIngredientes`),
  traerRecetaPorId: (id) =>
    api.post(`/TraerRecetasPorIdReceta`, { idReceta: id }),
};

export default recetaApi;
