export default function user(
  state = {
    isLogged: false,
    listaUsuarios: null,
  },
  action
) {
  switch (action.type) {
    case "LOGIN":
      return {
        ...state,
        usuarioActual: action.payload,
        isLogged: true,
        loginError: null,
      };
    case "LOGIN_ERROR":
      return {
        ...state,
        loginError: action.error,
      };
    case "TRAER_USUARIOS":
      return {
        ...state,
        listaUsuarios: action.payload.usuarios,
        traerUsuariosError: null,
      };
    case "TRAER_USUARIOS_ERROR":
      return {
        ...state,
        traerUsuariosError: action.error,
      };
    case "TRAER_USUARIOS_SEGUIDOS":
      return {
        ...state,
        listaUsuariosSeguidos: action.payload.usuarios,
        traerUsuariosSeguidosError: null,
      };
    case "TRAER_USUARIOS_SEGUIDOS_ERROR":
      return {
        ...state,
        traerUsuariosSeguidosError: action.error,
      };
    case "LOGOUT":
      return {
        ...state,
        isLogged: false,
      };
    default:
      return state;
  }
}
