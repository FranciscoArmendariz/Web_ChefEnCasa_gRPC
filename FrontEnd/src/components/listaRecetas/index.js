import Image from "next/image";
import { useRouter } from "next/router";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { interaccionApi } from "@/services/interacciones";
import { useDispatch, useSelector } from "react-redux";
import cn from "classnames";
import {
  traerRecetasFavoritas,
  traerRecetasPorUsuario,
} from "@/redux/recetas/actions";
import { useEffect } from "react";
import recetaApi from "@/services/receta";

export default function ListaRecetas({
  recetas,
  misRecetas,
  recetario,
  idRecetario,
}) {
  const router = useRouter();
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);
  const dispatch = useDispatch();

  useEffect(() => {
    if (idUsuario) {
      dispatch(traerRecetasFavoritas(idUsuario));
      dispatch(traerRecetasPorUsuario(idUsuario));
    }
  }, [dispatch, idUsuario]);

  const recetasFavoritas = useSelector(
    (state) => state.recetas.listaRecetasFavoritas
  );
  const recetasUsuario = useSelector(
    (state) => state.recetas.listaRecetasPorUsuario
  );

  const esFavorita = (recetaId) => {
    return recetasFavoritas?.some((receta) => receta.id === recetaId);
  };

  const removerRecetario = (idReceta) => {
    recetaApi
      .removerRecetaRecetario({
        idReceta: idReceta,
        idRecetario: idRecetario,
      })
      .then(dispatch(/*TODO Todo todo todo TODO*/));
  };

  const toggleFavorito = (idReceta, favorita) => {
    if (favorita) {
      interaccionApi
        .removerRecetaFavorita({ idUsuario, idReceta })
        .then((response) => {
          if (response.ok) {
            dispatch(traerRecetasFavoritas(idUsuario));
          }
        });
    } else {
      interaccionApi
        .agregarRecetaFavorita({ idUsuario, idReceta })
        .then((response) => {
          if (response.ok) {
            dispatch(traerRecetasFavoritas(idUsuario));
          }
        });
    }
  };

  return (
    <div className='flex flex-row flex-wrap justify-center gap-8'>
      {recetas?.map((receta) => {
        const favorita = esFavorita(receta.id);
        return (
          <div key={receta.id} className='relative'>
            <button
              className='flex flex-row border border-gray-300 rounded-lg relative w-64 shadow-md'
              onClick={() => {
                router.push(`/receta/${receta.id}`);
              }}
            >
              <div className='flex w-full flex-col relative h-32'>
                <Image
                  src={receta.fotos[0]?.url || "/"}
                  alt={receta.titulo}
                  className='rounded-lg'
                  style={{ objectFit: "cover" }}
                  fill
                />
                <div className='absolute w-full pr-10 pl-3  bottom-0 left-0 bg-white/80 rounded-lg font-semibold'>
                  {receta.titulo}
                </div>
                <div className='bg-white w-10 h-10 absolute bottom-0 right-0 rounded-lg rounded-tl-sm flex justify-center text-center items-center font-semibold'>
                  {receta.tiempoAprox}´
                </div>
              </div>
            </button>
            {!recetasUsuario?.some((r) => r.id === receta.id) && (
              <button
                onClick={() => {
                  toggleFavorito(receta.id, favorita);
                }}
                className='absolute right-0 top-0  bg-white/80 mt-1 mr-1 rounded-xl p-1 z-10'
              >
                <FeatherIcon
                  icon={favorita ? "x" : "star"}
                  size={25}
                  className={cn({
                    "stroke-1 fill-yellow-500": !favorita,
                    "stroke-2 text-red-500": favorita,
                  })}
                />
              </button>
            )}
            {(misRecetas || recetario) && (
              <button
                onClick={() => {
                  misRecetas
                    ? router.push(`/editarReceta/${receta.id}`)
                    : removerRecetario(receta.id);
                }}
                className='absolute left-0 top-0 bg-white/80 rounded-lg mt-1 ml-1 p-2 z-10'
              >
                <FeatherIcon
                  icon={misRecetas ? "edit" : "trash"}
                  size={25}
                  className={cn(
                    "stroke-2",
                    misRecetas ? "text-blue-800" : "text-red-600"
                  )}
                />
              </button>
            )}
          </div>
        );
      })}
    </div>
  );
}
