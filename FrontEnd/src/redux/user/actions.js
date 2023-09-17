import { userApi } from "@/services/user";

export const login =
  ({ usuario, contrasenia }) =>
  async (dispatch) => {
    userApi.login({ usuario, clave: contrasenia }).then((response) => {
      if (response.ok) {
        dispatch({ type: "LOGIN", payload: response.data });
      } else {
        dispatch({ type: "LOGIN_ERROR", error: response.problem });
      }
    });
  };

export const crearUsuario = (datos) => async (dispatch) => {
  userApi.crearUsuario(datos).then((response) => {
    if (response.ok) {
      dispatch({ type: "CREAR_USUARIO", payload: response.data });
    } else {
      dispatch({ type: "CREAR_USUARIO_ERROR", error: response.problem });
    }
  });
};

export const traerUsuarios = () => async (dispatch) => {
  userApi.traerUsuarios().then((response) => {
    if (response.ok) {
      dispatch({ type: "TRAER_USUARIOS", payload: response.data });
    } else {
      dispatch({ type: "TRAER_USUARIOS_ERROR", error: response.problem });
    }
  });
};

export const traerUsuariosSeguidos = (id) => async (dispatch) => {
  userApi.traerUsuariosSeguidos(id).then((response) => {
    if (response.ok) {
      dispatch({ type: "TRAER_USUARIOS_SEGUIDOS", payload: response.data });
    } else {
      dispatch({
        type: "TRAER_USUARIOS_SEGUIDOS_ERROR",
        error: response.problem,
      });
    }
  });
};

export function limpiarRespuestas() {
  return { type: "LIMPIAR_RESPUESTAS" };
}

export function logout() {
  return { type: "LOGOUT" };
}

export function traerUser({ campo, request }) {
  return { type: "TRAER", payload: { campo, request } };
}
