import recetaApi from "@/services/receta";

export function setFiltro(value) {
  return { type: "SET_FILTRO", payload: value };
}

export function limpiarListas() {
  return { type: "LIMPIAR_LISTAS" };
}

export function limpiarRespuestas() {
  return { type: "LIMPIAR_RESPUESTAS" };
}

export const crearReceta = (receta) => async (dispatch) => {
  recetaApi.crearReceta(receta).then((response) => {
    if (response.ok) {
      dispatch({ type: "CREAR_RECETA", payload: response.data });
    } else {
      dispatch({ type: "CREAR_RECETA_ERROR", error: response.problem });
    }
  });
};

export const editarReceta = (receta) => async (dispatch) => {
  recetaApi.editarReceta(receta).then((response) => {
    if (response.ok) {
      dispatch({ type: "EDITAR_RECETA", payload: response.data });
    } else {
      dispatch({ type: "EDITAR_RECETA_ERROR", error: response.problem });
    }
  });
};

export const traerRecetaPorId = (id) => async (dispatch) => {
  recetaApi.traerRecetaPorId(id).then((response) => {
    if (response.ok) {
      dispatch({ type: "TRAER_RECETA_ID", payload: response.data });
    } else {
      dispatch({ type: "TRAER_RECETA_ID_ERROR", error: response.problem });
    }
  });
};

export const traerRecetasPorUsuario = (idUsuario) => async (dispatch) => {
  recetaApi.traerRecetasPorUsuario(idUsuario).then((response) => {
    if (response.ok) {
      dispatch({
        type: "TRAER_RECETAS_USUARIO",
        payload: response.data.recetas,
      });
    } else {
      dispatch({
        type: "TRAER_RECETAS_USUARIO_ERROR",
        error: response.problem,
      });
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

export const traerIngredientes = () => async (dispatch) => {
  recetaApi.traerIngredientes().then((response) => {
    if (response.ok) {
      dispatch({
        type: "TRAER_INGREDIENTES",
        payload: response.data.ingredienteSin,
      });
    } else {
      dispatch({
        type: "TRAER_INGREDIENTES_ERROR",
        error: response.problem,
      });
    }
  });
};

export const traerRecetasNuevas = () => async (dispatch) => {
  recetaApi.traerRecetasNuevas().then((response) => {
    if (response.ok) {
      dispatch({
        type: "TRAER_RECETAS_NUEVAS",
        payload: response.data,
      });
    } else {
      dispatch({
        type: "TRAER_RECETAS_NUEVAS_ERROR",
        error: response.problem,
      });
    }
  });
};
