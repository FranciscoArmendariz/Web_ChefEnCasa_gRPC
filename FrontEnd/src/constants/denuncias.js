import { RECETAS } from "./recetas";

export const DENUNCIAS = [
  {
    id: 1,
    motivo: "Contenido inapropiado",
    estado: "pendiente",
    receta: RECETAS[0],
  },
  {
    id: 2,
    motivo: "Ingredientes prohibidos",
    estado: "pendiente",
    receta: RECETAS[0],
  },
  {
    id: 3,
    motivo: "Contenido inapropiado",
    estado: "resuelto",
    receta: RECETAS[0],
  },
  {
    id: 4,
    motivo: "Peligroso para la salud",
    estado: "pendiente",
    receta: RECETAS[0],
  },
  {
    id: 5,
    motivo: "Peligroso para la salud",
    estado: "resuelto",
    receta: RECETAS[0],
  },
];
