import api from "@/config/api";

const RUTA = "/Receta";

const recetaApi = {
  getRecetas: () => api.get(`${RUTA}`),
  getRecetasFavoritas: ({ idUsuario, idPagina }) =>
    api.post(`${RUTA}`, { idUsuario, idPagina }),
  getRecetasPorUsuario: ({ idUsuario }) => api.post(`${RUTA}`, { idUsuario }),
  createRecipe: (recipe) => api.post("/recipes", recipe),
};

export default recetaApi;
