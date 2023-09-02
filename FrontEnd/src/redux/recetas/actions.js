export function setFiltro(value) {
  return { type: "SET_FILTRO", payload: value };
}

export function traer({ campo, service }) {
  return { type: "TRAER", payload: { campo, service } };
}

export function reset() {
  return { type: "RESET" };
}
