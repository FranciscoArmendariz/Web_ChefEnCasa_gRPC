import Image from "next/image";
import { useRouter } from "next/router";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { interaccionApi } from "@/services/interacciones";
import { useDispatch, useSelector } from "react-redux";
import cn from "classnames";
import { traerRecetasFavoritas } from "@/redux/recetas/actions";

export default function ListaRecetas({ recetas, favoritas }) {
  const router = useRouter();
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);
  const dispach = useDispatch();

  const toggleFavorito = (idReceta) => {
    if (favoritas) {
      interaccionApi
        .removerRecetaFavorita({ idUsuario, idReceta })
        .then((response) => {
          if (response.ok) {
            dispach(traerRecetasFavoritas(idUsuario));
          }
        });
    } else {
      interaccionApi.agregarRecetaFavorita({ idUsuario, idReceta });
    }
  };

  return (
    <div className='flex flex-row flex-wrap justify-center gap-8'>
      {recetas?.map((receta) => {
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
            <button
              onClick={() => {
                toggleFavorito(receta.id);
              }}
              className='absolute right-0 top-0  bg-white mt-1 mr-1 rounded-xl p-1 z-10'
            >
              <FeatherIcon
                icon={favoritas ? "x" : "star"}
                size={25}
                className={cn({
                  "stroke-1 fill-yellow-500": !favoritas,
                  "stroke-2 text-red-500": favoritas,
                })}
              />
            </button>
          </div>
        );
      })}
    </div>
  );
}
