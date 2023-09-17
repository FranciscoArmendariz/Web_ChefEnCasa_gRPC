import LoadingWrapper from "@/components/LoadingWrapper";
import { limpiarListas, traerRecetaPorId } from "@/redux/recetas/actions";
import Image from "next/image";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Receta({ idReceta }) {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(limpiarListas());
    if (!!idReceta) {
      dispatch(traerRecetaPorId(idReceta));
    }
  }, [dispatch, idReceta]);

  const receta = useSelector((state) => state.recetas.recetaPorId);

  return (
    <LoadingWrapper loading={!receta}>
      <div className='mx-6'>
        <ul className='list-none'>
          <li>TITULO: {receta?.titulo}</li>
          <li>DECRIPCIÓN: {receta?.descripcion}</li>
          <li>CATEGORÍA: {receta?.categoria}</li>
          <li>TIEMPO APROXIMADO DE PREPARACIÓN: {receta?.tiempoAprox}</li>
          <li>TITULO:{receta?.titulo}</li>
          <li>
            INGREDIENTES:
            <ul className='list-disc pl-10'>
              {receta?.ingredientes?.map((ingrediente) => {
                return (
                  <li key={`${ingrediente.id}-${ingrediente.nombre}`}>
                    {ingrediente.nombre}: {ingrediente.cantidad}
                  </li>
                );
              })}
            </ul>
          </li>
          <li>
            PASOS:
            <ul className='list-decimal pl-10'>
              {receta?.pasos?.map((paso) => {
                return (
                  <li key={`${paso.id}-${paso.numero}`}>{paso.descripcion}</li>
                );
              })}
            </ul>
          </li>
          <li>
            IMPÁGENES:
            <div className='pl-6'>
              {receta?.fotos?.map((foto) => {
                return (
                  foto?.url && (
                    <Image
                      key={foto?.url}
                      src={foto?.url || "/"}
                      width={150}
                      height={150}
                    />
                  )
                );
              })}
            </div>
          </li>
        </ul>
      </div>
    </LoadingWrapper>
  );
}
