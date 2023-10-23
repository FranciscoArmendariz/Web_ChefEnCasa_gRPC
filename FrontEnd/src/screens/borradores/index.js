import { useState } from "react";
import Papa from "papaparse";
import { borradorApi } from "@/services/borradores";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { useRouter } from "next/router";

export default function Borradores() {
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);
  const [borradores, setBorradores] = useState();
  const router = useRouter();

  useEffect(() => {
    idUsuario &&
      borradorApi
        .traerBorradores(idUsuario)
        .then((response) => setBorradores(response.data));
  }, [idUsuario]);

  const csvToJSON = (file) => {
    if (Array.isArray(file)) {
      return file.reduce(
        (array, recetaActual) => [
          ...array,
          {
            titulo: recetaActual[0],
            descripcion: recetaActual[1],
            categoria: recetaActual[2],
            tiempoAprox: recetaActual[3],
          },
        ],
        []
      );
    }
    return {
      titulo: recetaActual[0],
      descripcion: recetaActual[1],
      categoria: recetaActual[2],
      tiempoAprox: recetaActual[3],
    };
  };

  const handleFileUpload = (e) => {
    const files = e.target.files;
    if (files) {
      Papa.parse(files[0], {
        complete: (results) => {
          const json = csvToJSON(results.data);
          borradorApi
            .crearBorrador({ borradores: json, idUsuario: idUsuario })
            .then(() =>
              borradorApi
                .traerBorradores(idUsuario)
                .then((response) => setBorradores(response.data))
            );
        },
      });
    }
  };

  return (
    <div className='flex flex-row'>
      <div className='w-1/2 flex justify-center'>
        <div className='p-11 flex justify-center items-center m-5 h-20 w-3/4 bg-blue-400 text-white rounded-lg'>
          <input
            type='file'
            accept='.csv'
            onChange={(e) => {
              handleFileUpload(e);
            }}
          />
        </div>
      </div>
      <div className='w-1/2 m-5'>
        <h1 className='text-center font-bold text-xl pb-6'>BORRADORES</h1>
        <div className='flex flex-col items-center gap-5'>
          {borradores &&
            borradores.map((borrador) => {
              return (
                <button
                  onClick={() => router.push(`borrador/${borrador.id}`)}
                  key={borrador.id}
                  className='bg-yellow-500 flex flex-row p-6 justify-between w-52 rounded-lg shadow-lg'
                >
                  <div>Borrador {borrador.id}</div>
                  <FeatherIcon icon={"edit"} />
                </button>
              );
            })}
        </div>
      </div>
    </div>
  );
}
