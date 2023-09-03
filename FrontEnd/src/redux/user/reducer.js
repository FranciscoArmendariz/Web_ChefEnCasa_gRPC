export default function user(
  state = {
    usuario: null,
    contrasenia: null,
    nombre: null,
    mail: null,
    id: null,
    isLogged: false,
    listaUsuarios: null,
  },
  action
) {
  switch (action.type) {
    case "LOGIN":
      return {
        ...state,
        usuario: action.payload.usuario,
        contrasenia: action.payload.contrasenia,
        nombre: "NombrePrueba",
        mail: "Mail_de_prueba@Tmail.com",
        isLogged: true,
      };
    case "LOGOUT":
      return {
        ...state,
        usuario: undefined,
        contrasenia: undefined,
        isLogged: false,
      };
    case "TRAER":
      return {
        ...state,
        [action.payload.campo]:
          typeof action.payload.service === "function"
            ? action.payload.service()
            : action.payload.service,
      };
    default:
      return state;
  }
}
