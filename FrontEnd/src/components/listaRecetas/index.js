import Image from "next/image";
import { useRouter } from "next/router";

export default function ListaRecetas({ recetas }) {
  const router = useRouter();

  return (
    <div className='flex flex-row flex-wrap justify-center gap-8'>
      {recetas.slice(0, 12).map((receta) => {
        return (
          <button
            key={receta.id}
            className='flex flex-row border border-gray-300 rounded-lg relative w-64 shadow-md'
            onClick={() => {
              router.push(`/receta/${receta.id}`);
            }}
          >
            <div className='flex w-full flex-col relative h-32'>
              <Image
                src={receta.imagenes[0]}
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
        );
      })}
    </div>
  );
}
