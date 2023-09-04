import { useEffect } from "react";
import FormularioFiltros from "./components/formularioFiltros";
import ListaRecetas from "@/components/listaRecetas";
import LoadingWrapper from "@/components/LoadingWrapper";
import { useDispatch, useSelector } from "react-redux";
import { traer, traerRecetasConFiltros } from "@/redux/recetas/actions";
import { RECETAS } from "@/constants/recetas";
import recetaApi from "@/services/receta";

export default function Recetas() {
  const dispach = useDispatch();
  const filtros = useSelector((state) => state.recetas.filtroActual);

  useEffect(() => {
    dispach(traerRecetasConFiltros(filtros));
  }, [filtros]);

  const recetas = useSelector((state) => state.recetas.listaRecetas);

  return (
    <div className='pt-10 pb-4 flex flex-row max-w-7xl m-auto h-max'>
      <div className='w-full'>
        <LoadingWrapper loading={!recetas}>
          <ListaRecetas recetas={recetas} />
        </LoadingWrapper>
      </div>
      <div className='border bg-gradient-to-br from-orange-300 to-orange-500 rounded-xl mx-6 w-96 pt-2 pb-10 shadow-lg shadow-gray-400'>
        <FormularioFiltros />
      </div>
    </div>
  );
}
