import api from "@/api";
import recetaApi from "@/services/receta";
import { useDispatch } from "react-redux";

export function setFiltro(value) {
  return { type: "SET_FILTRO", payload: value };
}

export const traerRecetaPorId = (id) => async (dispatch) => {
  recetaApi.traerRecetaPorId(id).then((response) => {
    if (response.ok) {
      dispatch({ type: "TRAER_RECETA_ID", payload: response.data });
    } else {
      dispatch({ type: "TRAER_RECETA_ID_ERROR", error: response.problem });
    }
  });
};

export const traerRecetasFavoritas = (id) => async (dispatch) => {
  recetaApi.traerRecetasFavoritas(id).then((response) => {
    if (response.ok) {
      dispatch({
        type: "TRAER_RECETAS_FAVORITAS",
        payload: response.data.recetas,
      });
    } else {
      dispatch({
        type: "TRAER_RECETAS_FAVORITAS_ERROR",
        error: response.problem,
      });
    }
  });
};

export const traerRecetasConFiltros = (filtros) => async (dispatch) => {
  recetaApi.traerRecetasConFiltros(filtros).then((response) => {
    if (response.ok) {
      dispatch({
        type: "TRAER_RECETAS_CON_FILTOS",
        payload: response.data.recetas,
      });
    } else {
      dispatch({
        type: "TRAER_RECETAS_CON_FILTOS_ERROR",
        error: response.problem,
      });
    }
  });
};
