import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { RECETARIOS } from "@/constants/recetas";
import { limpiarListas, traerRecetasPorUsuario } from "@/redux/recetas/actions";
import recetaApi from "@/services/receta";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Recetario({ idRecetario }) {
  const [recetario, setRecetario] = useState();

  useEffect(() => {
    idRecetario &&
      recetaApi
        .traerRecetario(idRecetario)
        .then((response) =>
          setRecetario(
            RECETARIOS.find((recetario) => recetario.id === idRecetario)
          )
        );
  }, [idRecetario]);

  const recetas = recetario?.recetas;

  const removerRecetario = (idReceta) => {
    recetaApi
      .removerRecetaRecetario({
        idReceta: idReceta,
        idRecetario: idRecetario,
      })
      .then(() =>
        recetaApi
          .traerRecetario(idRecetario)
          .then((response) =>
            setRecetario(
              RECETARIOS.find((recetario) => recetario.id === idRecetario)
            )
          )
      );
  };

  return (
    <div className='flex flex-col items-center'>
      <LoadingWrapper loading={!recetario}>
        <>
          <h2 className='uppercase font-bold text-xl p-2'>
            {recetario?.nombre}
          </h2>
          <ListaRecetas
            recetas={recetas}
            recetario
            removerRecetario={removerRecetario}
            idRecetario={idRecetario}
          />
        </>
      </LoadingWrapper>
    </div>
  );
}
