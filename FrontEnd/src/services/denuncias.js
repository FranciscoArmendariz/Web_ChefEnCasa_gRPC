import { create } from "apisauce";

const api = create({
  baseURL: "http://localhost:8000",
});

const RUTA = "/api/denuncia";

export const denunciaApi = {
  crearDenuncia: (idReceta, motivo) =>
    api.post(`${RUTA}/crear`, { idReceta, motivo }),
  traerDenuncias: () => api.get(`${RUTA}/denuncias`),
  resolverDenuncia: (idDenuncia, eliminar) =>
    api.post(`${RUTA}/resolver`, { idDenuncia, eliminar }),
};
