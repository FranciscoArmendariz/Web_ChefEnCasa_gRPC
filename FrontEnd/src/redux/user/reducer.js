export default function user(
  state = {
    isLogged: false,
    listaUsuarios: null,
  },
  action
) {
  switch (action.type) {
    case "LIMPIAR_RESPUESTAS":
      return {
        ...state,
        crearUsuarioRespuesta: null,
      };
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
    case "CREAR_USUARIO":
      return {
        ...state,
        crearUsuarioRespuesta: action.payload,
        crearUsuarioError: null,
      };
    case "CREAR_USUARIO_ERROR":
      return {
        ...state,
        crearUsuarioError: action.error,
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
        usuarioActual: null,
      };
    default:
      return state;
  }
}
