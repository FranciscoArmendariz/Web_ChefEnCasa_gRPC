import api from "@/api";

const RUTA = "User";

export const userApi = {
  traerUsuarios: () => api.post(`${RUTA}/TraerUsuarios`, {}),
  login: ({ usuario, clave }) => api.post(`${RUTA}/Login`, { usuario, clave }),
  crearUsuario: (value) => api.post(`${RUTA}/CrearUsuario`, value),
  traerUsuariosSeguidos: (id) =>
    api.post(`${RUTA}/TraerUsuariosSeguidos`, { id }),
  crearMensaje: ({ idAutor, idReceptor, mensaje, asunto }) =>
    api.post(`${RUTA}/CrearMensaje`, { idAutor, idReceptor, mensaje, asunto }),
  responderMensaje: ({ idMensaje, respuesta }) =>
    api.post(`${RUTA}/ResponderMensaje`, { idMensaje, respuesta }),
};
