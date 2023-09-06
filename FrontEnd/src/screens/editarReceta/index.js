import LoadingWrapper from "@/components/LoadingWrapper";
import FormularioReceta from "@/components/formularioReceta";
import {
  editarReceta,
  limpiarListas,
  limpiarRespuestas,
  traerRecetaPorId,
} from "@/redux/recetas/actions";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function EditarReceta({ idReceta }) {
  const editarRecetaRespuesta = useSelector(
    (state) => state.recetas.editarRecetaRespuesta
  );
  const router = useRouter();
  const dispatch = useDispatch();

  const onSubmit = (data) => {
    dispatch(
      editarReceta({
        receta: {
          ...data,
          idReceta: idReceta,
          fotos: undefined,
          pasos: undefined,
          ingredientes: undefined,
        },
        fotos: data.fotos,
        ingredientes: data.ingredientes,
        pasos: data.pasos,
      })
    );
  };
  useEffect(() => {
    if (idReceta) {
      dispatch(traerRecetaPorId(idReceta));
    }
    return () => {
      dispatch(limpiarListas());
    };
  }, [idReceta]);
  useEffect(() => {
    if (editarRecetaRespuesta) {
      dispatch(limpiarRespuestas());
      router.push("/");
    }
  }, [editarRecetaRespuesta]);

  const recetaEditar = useSelector((state) => state.recetas.recetaPorId);

  return (
    <div className='px-10 flex flex-col items-center'>
      <h1 className='font-bold text-lg my-3'>EDITAR RECETA</h1>
      <LoadingWrapper loading={!recetaEditar}>
        <div className='w-full'>
          <FormularioReceta
            onSubmit={onSubmit}
            editar
            recetaEditar={recetaEditar}
          />
        </div>
      </LoadingWrapper>
    </div>
  );
}
