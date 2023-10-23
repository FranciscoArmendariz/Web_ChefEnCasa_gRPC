import LoadingWrapper from "@/components/LoadingWrapper";
import { DENUNCIAS } from "@/constants/denuncias";
import { denunciaApi } from "@/services/denuncias";
import recetaApi from "@/services/receta";
import { data } from "autoprefixer";
import Image from "next/image";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";

export default function Denuncias() {
  const [denuncias, setDenuncias] = useState();
  const router = useRouter();

  useEffect(() => {
    denunciaApi
      .traerDenuncias()
      .then((response) => setDenuncias(response.data));
  }, []);

  const handleEliminarReceta = (idDenuncia) => {
    denunciaApi
      .resolverDenuncia(idDenuncia, true)
      .then(() =>
        denunciaApi
          .traerDenuncias()
          .then((response) => setDenuncias(response.data))
      );
  };
  const handleIgnorarDenuncia = (idDenuncia) => {
    denunciaApi
      .resolverDenuncia(idDenuncia, false)
      .then(() =>
        denunciaApi
          .traerDenuncias()
          .then((response) => setDenuncias(response.data))
      );
  };
  console.log(denuncias);

  return (
    <div className='p-3 flex flex-col items-center'>
      <h1 className='font-bold text-3xl'>DENUNCIAS</h1>
      <div className='flex flex-col gap-4 w-full pt-16 items-center'>
        <LoadingWrapper loading={!denuncias}>
          {denuncias &&
            denuncias.length > 0 &&
            denuncias.map((denuncia) => {
              return (
                denuncia.estado === "nuevo" && (
                  <div className='shadow-xl rounded-lg bg-white w-1/3 h-36 flex flex-row'>
                    <button
                      className='flex flex-row border border-gray-300 rounded-lg relative w-96 shadow-md'
                      onClick={() => {
                        router.push(`/receta/${denuncia.receta.id}`);
                      }}
                    >
                      <div className='flex w-full flex-col relative h-full'>
                        <Image
                          src={denuncia.receta.fotos[0]?.url || "/"}
                          alt={denuncia.receta.titulo}
                          className='rounded-lg'
                          style={{ objectFit: "cover" }}
                          fill
                        />
                        <div className='absolute w-full pr-10 pl-3  bottom-0 left-0 bg-white/80 rounded-lg font-semibold'>
                          {denuncia.receta.titulo}
                        </div>
                        <div className='bg-white w-10 h-10 absolute bottom-0 right-0 rounded-lg rounded-tl-sm flex justify-center text-center items-center font-semibold'>
                          {denuncia.receta.tiempoAprox}´
                        </div>
                      </div>
                    </button>
                    <div className='pl-3 py-3 flex flex-col justify-around w-full'>
                      <span className='font-semibold text-lg'>
                        MOTIVO: {denuncia.motivo}
                      </span>
                      <div className='flex flex-row justify-start'>
                        <button
                          onClick={() => handleEliminarReceta(denuncia.id)}
                          className='bg-red-600 text-white p-2 rounded-lg font-semibold mr-5'
                        >
                          Borrar Receta
                        </button>
                        <button
                          onClick={() => handleIgnorarDenuncia(denuncia.id)}
                          className='bg-green-700 text-white p-2 rounded-lg font-semibold'
                        >
                          Ignorar Denuncia
                        </button>
                      </div>
                    </div>
                  </div>
                )
              );
            })}
        </LoadingWrapper>
      </div>
    </div>
  );
}
