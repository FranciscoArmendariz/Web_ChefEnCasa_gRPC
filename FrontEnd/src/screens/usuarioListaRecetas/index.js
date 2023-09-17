import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { limpiarListas, traerRecetasPorUsuario } from "@/redux/recetas/actions";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function UsuarioListaRecetas({ idUsuario, nombre, misRecetas }) {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(limpiarListas());
    if (idUsuario) {
      dispatch(traerRecetasPorUsuario(idUsuario));
    }
  }, [idUsuario, dispatch]);

  const recetasUsuario = useSelector(
    (state) => state.recetas.listaRecetasPorUsuario
  );

  return (
    <div className='flex flex-col items-center'>
      <h2 className='uppercase font-bold text-xl p-2'>RECETAS DE {nombre}</h2>
      <LoadingWrapper loading={!recetasUsuario}>
        <ListaRecetas recetas={recetasUsuario} misRecetas={misRecetas} />
      </LoadingWrapper>
    </div>
  );
}
