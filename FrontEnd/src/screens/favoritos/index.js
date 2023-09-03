import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { traer } from "@/redux/recetas/actions";
import LoadingWrapper from "@/components/LoadingWrapper";
import ListaRecetas from "@/components/listaRecetas";
import { RECETAS } from "@/constants/recetas";

export default function Favoritos() {
  const dispach = useDispatch();

  useEffect(() => {
    dispach(traer({ campo: "listaRecetasFavoritas", service: RECETAS }));
  }, [dispach]);

  const recetasFavoritas = useSelector(
    (state) => state.recetas.listaRecetasFavoritas
  );
  return (
    <div className='py-10'>
      <LoadingWrapper loading={!!!recetasFavoritas}>
        <ListaRecetas recetas={recetasFavoritas} />
      </LoadingWrapper>
    </div>
  );
}
