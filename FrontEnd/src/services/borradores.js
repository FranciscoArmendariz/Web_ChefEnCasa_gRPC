import { create } from "apisauce";

const api = create({
  baseURL: "http://localhost:8000",
});

const RUTA = "/api/borrador";

export const borradorApi = {
  crearBorrador: (value) => api.post(`${RUTA}/crear`, value),
  traerBorradores: (idUsuario) => api.get(`${RUTA}/usuario/${idUsuario}`),
  traerBorrador: (id) => api.get(`${RUTA}/${id}`),
  editarBorrador: (id, recetas) =>
    api.put(`${RUTA}/editar/${id}`, { borradores: recetas }),
  crearRecetas: (idBorrador, borradores) =>
    api.post(`${RUTA}/crear-receta`, { idBorrador, borradores }),
};
