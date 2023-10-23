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
  crearMensaje: ({ idAutor, idReceptor, asunto, mensaje }) =>
    api.post(`${RUTA}/CrearMensaje`, {
      idAutor,
      idReceptor,
      asunto,
      mensaje,
      respuesta: "",
    }),
  responderMensaje: ({ idMensaje, respuesta }) =>
    api.post(`${RUTA}/responderMensaje`, { idMensaje, respuesta }),
  traerMensajesPorAutor: (idAutor) =>
    api.post(`${RUTA}/TraerMensajesPorAutor`, { idAutor }),
  traerMensajesPorReceptor: (idReceptor) =>
    api.post(`${RUTA}/TraerMensajesPorReceptor`, { idReceptor }),
};
