import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { RECETARIOS } from "@/constants/recetas";
import { limpiarListas, traerRecetasPorUsuario } from "@/redux/recetas/actions";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Recetario({ idRecetario }) {
  const dispatch = useDispatch();

  /*
  useEffect(() => {
    dispatch(limpiarListas());
    if (idUsuario) {
      dispatch(traerRecetasPorUsuario(idUsuario));
    }
  }, [idUsuario, dispatch]);

  const recetasUsuario = useSelector(
    (state) => state.recetas.listaRecetasPorUsuario
  );
  */

  const recetario = RECETARIOS.find(
    (recetario) => recetario.id === idRecetario
  );
  const recetas = recetario.recetas;

  return (
    <div className='flex flex-col items-center'>
      <h2 className='uppercase font-bold text-xl p-2'>{recetario.nombre}</h2>
      <LoadingWrapper loading={!recetas}>
        <ListaRecetas recetas={recetas} recetario idRecetario={idRecetario} />
      </LoadingWrapper>
    </div>
  );
}
