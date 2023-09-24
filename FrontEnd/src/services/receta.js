import api from "@/api";

const RUTA = "";

const recetaApi = {
  traerRecetasConFiltros: (filtros) =>
    api.post(`${RUTA}/TraerRecetas`, filtros),
  traerRecetas: () => api.post(`${RUTA}/TraerTodasLasRecetas`),
  crearReceta: (value) => api.post(`${RUTA}/CrearReceta`, value),
  editarReceta: (value) =>
    console.log("value", value) || api.post(`${RUTA}/EditarReceta`, value),
  traerRecetasPorUsuario: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasPorId`, { id: idUsuario }),
  traerRecetasFavoritas: (idUsuario) =>
    api.post(`${RUTA}/TraerRecetasFavoritas`, { id: idUsuario }),
  traerIngredientes: () => api.post(`${RUTA}/TraerTodosLosIngredientes`, {}),
  traerRecetaPorId: (id) =>
    api.post(`/TraerRecetasPorIdReceta`, { idReceta: id }),
};

export default recetaApi;
