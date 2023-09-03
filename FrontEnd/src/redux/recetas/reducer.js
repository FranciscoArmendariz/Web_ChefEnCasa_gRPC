import recetaApi from "@/services/receta";

export default function recetas(
  state = {
    filtroActual: {},
    listaRecetas: null,
    listaRecetasFavoritas: null,
    recetaPorId: null,
  },
  action
) {
  switch (action.type) {
    case "SET_FILTRO":
      return { ...state, filtroActual: action.payload };
    case "TRAER":
      return {
        ...state,
        [action.payload.campo]:
          typeof action.payload.service === "function"
            ? action.payload.service()
            : action.payload.service,
      };
    case "TRAER_POR_ID":
      return {
        ...state,
        recetaPorId: recetaApi.getRecetasPorId(action.payload),
      };
    default:
      return state;
  }
}
