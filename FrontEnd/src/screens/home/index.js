import ListaRecetas from "@/components/listaRecetas";
import { TOP_RECETAS } from "@/constants/recetas";
import { traerRecetasFavoritas } from "@/redux/recetas/actions";
import Image from "next/image";
import { useDispatch, useSelector } from "react-redux";

export default function Home() {
  const dispatch = useDispatch();

  /*
  useEffect(() => {
    dispatch(traerRecetasFavoritas());
  }, []);

  const recetasNuevas = useSelector(
    (state) => state.recetas.listaRecetasNuevas
  );*/

  const recetasNuevas = TOP_RECETAS;

  return (
    <div className='pt-8 px-14 flex flex-col justify-center'>
      <h1 className='flex-1 font-bold text-5xl text-center'>CHEF EN CASA</h1>
      <div className='flex-1'>
        <h2 className='font-bold text-2xl text-center pt-4 pb-4'>
          RECETAS NUEVAS
        </h2>
        <div className='max-w-4xl m-auto pb-5 flex flex-row flex-wrap justify-center gap-8'>
          {recetasNuevas.map((receta) => {
            return (
              <div
                key={receta.titulo}
                className='flex flex-row border border-gray-300 rounded-lg relative w-64 shadow-md'
              >
                <div className='flex w-full flex-col relative h-40'>
                  {receta?.foto && (
                    <Image
                      src={receta.foto}
                      alt={receta.titulo}
                      className='rounded-lg'
                      style={{ objectFit: "cover" }}
                      fill
                    />
                  )}
                  <div className='absolute w-full pr-10 pl-3  bottom-0 left-0 bg-white/80 rounded-lg '>
                    <div className='font-semibold'>{receta.titulo}</div>
                    Autor: {receta.autor}
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
