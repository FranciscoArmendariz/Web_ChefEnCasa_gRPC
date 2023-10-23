import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { limpiarListas, traerRecetasPorUsuario } from "@/redux/recetas/actions";
import recetaApi from "@/services/receta";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function UsuarioListaRecetas({ idUsuario, nombre, misRecetas }) {
  const dispatch = useDispatch();
  const [recetasUsuario, setRecetasUsuario] = useState(null);

  useEffect(() => {
    if (idUsuario) {
      recetaApi
        .traerRecetasPorUsuario(idUsuario)
        .then((response) => setRecetasUsuario(response.data.recetas));
    }
  }, [idUsuario, dispatch]);

  return (
    <div className='flex flex-col items-center'>
      <h2 className='uppercase font-bold text-xl p-2'>RECETAS DE {nombre}</h2>
      <LoadingWrapper loading={!recetasUsuario}>
        <ListaRecetas recetas={recetasUsuario} misRecetas={misRecetas} />
      </LoadingWrapper>
    </div>
  );
}
