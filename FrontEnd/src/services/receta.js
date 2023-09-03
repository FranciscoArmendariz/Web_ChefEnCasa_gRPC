import api from "@/api";

const RUTA = "";

const recetaApi = {
  getRecetas: (value) => api.post(`${RUTA}/TraerRecetas`, value),
  getRecetasFavoritas: ({ idUsuario, idPagina }) =>
    api.post(`${RUTA}`, { idUsuario, idPagina }),
  getRecetasPorUsuario: ({ idUsuario }) => api.post(`${RUTA}`, { idUsuario }),
  getRecetasPorId: ({ idReceta }) =>
    api.post(`/TraerRecetasPorId`, { id: idReceta }),
  createRecipe: (recipe) => api.post("/recipes", recipe),
};

export default recetaApi;
