import recetaApi from "@/services/receta";

export default function recetas(
  state = {
    filtroActual: {
      titulo: "",
      categoria: "",
      page: 1,
      size: 12,
      orderBy: "asc",
      sortBy: "id",
    },
    listaRecetas: null,
    listaRecetasFavoritas: null,
    recetaPorId: null,
  },
  action
) {
  switch (action.type) {
    case "SET_FILTRO":
      return { ...state, filtroActual: action.payload };
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
    default:
      return state;
  }
}
