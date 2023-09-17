import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  limpiarListas,
  traer,
  traerRecetasFavoritas,
} from "@/redux/recetas/actions";
import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { RECETAS } from "@/constants/recetas";

export default function Favoritos() {
  const dispatch = useDispatch();
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);

  useEffect(() => {
    dispatch(limpiarListas());
    if (idUsuario) {
      dispatch(traerRecetasFavoritas(idUsuario));
    }
  }, [dispatch, idUsuario]);

  const recetasFavoritas = useSelector(
    (state) => state.recetas.listaRecetasFavoritas
  );
  return (
    <div className='py-10'>
      <LoadingWrapper loading={!!!recetasFavoritas}>
        <ListaRecetas recetas={recetasFavoritas} favoritas />
      </LoadingWrapper>
    </div>
  );
}
