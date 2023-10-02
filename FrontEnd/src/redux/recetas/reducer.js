import recetaApi from "@/services/receta";

export default function recetas(
  state = {
    filtroActual: {
      titulo: "",
      categoria: "",
      page: 1,
      size: 50,
      orderBy: "asc",
      sortBy: "id",
    },
    listaRecetas: null,
    listaRecetasFavoritas: null,
    listaRecetasPorUsuario: null,
    listaRecetasNuevas: null,
    recetaPorId: null,
    ingredientes: [],
  },
  action
) {
  switch (action.type) {
    case "SET_FILTRO":
      return { ...state, filtroActual: action.payload };
    case "LIMPIAR_LISTAS":
      return {
        ...state,
        listaRecetasFavoritas: null,
        listaRecetasPorUsuario: null,
        recetaPorId: null,
      };
    case "LIMPIAR_RESPUESTAS":
      return {
        ...state,
        crearRecetaRespuesta: null,
        crearRecetaError: null,
        editarRecetaRespuesta: null,
        editarRecetaError: null,
      };
    case "CREAR_RECETA":
      return {
        ...state,
        crearRecetaRespuesta: action.payload,
        crearRecetaError: null,
      };
    case "CREAR_RECETA_ERROR":
      return {
        ...state,
        crearRecetaError: action.error,
      };
    case "EDITAR_RECETA":
      return {
        ...state,
        editarRecetaRespuesta: action.payload,
        editarRecetaError: null,
      };
    case "EDITAR_RECETA_ERROR":
      return {
        ...state,
        editarRecetaError: action.error,
      };
    case "TRAER_RECETAS_CON_FILTOS":
      return {
        ...state,
        listaRecetas: action.payload,
        listaRecetasError: null,
      };
    case "TRAER_RECETAS_CON_FILTOS_ERROR":
      return {
        ...state,
        listaRecetasError: action.error,
      };
    case "TRAER_RECETAS_FAVORITAS":
      return {
        ...state,
        listaRecetasFavoritas: action.payload,
        listaRecetasFavoritasError: null,
      };
    case "TRAER_RECETAS_FAVORITAS_ERROR":
      return {
        ...state,
        listaRecetasFavoritasError: action.error,
      };
    case "TRAER_RECETA_ID":
      return {
        ...state,
        recetaPorId: action.payload,
        recetaPorIderror: null,
      };
    case "TRAER_RECETA_ID_ERROR":
      return {
        ...state,
        recetaPorIderror: action.error,
      };
    case "TRAER_RECETAS_USUARIO":
      return {
        ...state,
        listaRecetasPorUsuario: action.payload,
        recetasPorUsuarioerror: null,
      };
    case "TRAER_RECETAS_USUARIO_ERROR":
      return {
        ...state,
        recetasPorUsuarioerror: action.error,
      };
    case "TRAER_INGREDIENTES":
      return {
        ...state,
        ingredientes: action.payload,
        ingredienteserror: null,
      };
    case "TRAER_INGREDIENTES_ERROR":
      return {
        ...state,
        ingredienteserror: action.error,
      };
    case "TRAER_RECETAS_NUEVAS":
      return {
        ...state,
        listaRecetasNuevas: action.payload,
        recetasNuevasError: null,
      };
    case "TRAER_RECETAS_NUEVAS_ERROR":
      return {
        ...state,
        recetasNuevasError: action.error,
      };
    case "NUEVO_COMENTARIO":
      return {
        ...state,
        nuevoComentarioRespuesta: action.payload,
        nuevoComentarioError: null,
      };
    case "NUEVO_COMENTARIO_ERROR":
      return {
        ...state,
        nuevoComentarioError: action.error,
      };
    default:
      return state;
  }
}
