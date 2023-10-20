import { useState } from "react";
import Papa from "papaparse";
import { borradorApi } from "@/services/borradores";
import { useSelector } from "react-redux";

export default function Borradores() {
  const [file, setFile] = useState(null);
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);

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
            .then((response) => console.log(response));
        },
      });
    }
  };

  return (
    <div>
      <div className='p-11 w-1/2 m-5 bg-blue-400 text-white rounded-lg'>
        <input
          type='file'
          accept='.csv'
          onChange={(e) => {
            handleFileUpload(e);
          }}
          placeholder='hola'
        />
      </div>
    </div>
  );
}
