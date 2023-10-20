import { create } from "apisauce";

const api = create({
  baseURL: "http://localhost:8000",
});

const RUTA = "/api/borrador";

export const borradorApi = {
  crearBorrador: (value) => api.post(`${RUTA}/crear`, value),
  traerBorrador: (id) => api.get(`${RUTA}/usuario/${id}`),
};
