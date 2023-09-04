import api from "@/api";

const RUTA = "/Interaccion";

export const interaccionApi = {
  seguirUsuario: ({ idSeguir, idSeguidor }) =>
    api.post(`${RUTA}/seguirUsuario`, { idSeguir, idSeguidor }),
  dejarDeSeguirUsuario: ({ idSeguir, idSeguidor }) =>
    api.post(`${RUTA}/dejarDeSeguirUsuario`, { idSeguir, idSeguidor }),
  agregarRecetaFavorita: ({ idUsuario, idReceta }) =>
    api.post(`${RUTA}/agregarFavorito`, { idUsuario, idReceta }),
  removerRecetaFavorita: ({ idUsuario, idReceta }) =>
    api.post(`${RUTA}/removerFavorito`, { idUsuario, idReceta }),
};
