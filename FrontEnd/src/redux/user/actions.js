export function login({ usuario, contrasenia }) {
  return { type: "LOGIN", payload: { usuario, contrasenia } };
}

export function logout() {
  return { type: "LOGOUT" };
}

export function traerUser({ campo, request }) {
  return { type: "TRAER", payload: { campo, request } };
}
