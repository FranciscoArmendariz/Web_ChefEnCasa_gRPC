import api from "@/config/api";

const RUTA = "/Receta";

const recetaApi = {
  getRecetas: () => api.get(`${RUTA}`),
  getRecetasFavoritas: ({ idUsuario, idPagina }) =>
    api.post(`${RUTA}`, { idUsuario, idPagina }),
  getRecetasPorUsuario: ({ idUsuario }) => api.post(`${RUTA}`, { idUsuario }),
  getRecetasPorId: ({ idReceta }) =>
    api.post(`/TraerRecetasPorId`, { id: idReceta }),
  createRecipe: (recipe) => api.post("/recipes", recipe),
};

export default recetaApi;
