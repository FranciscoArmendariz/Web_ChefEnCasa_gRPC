import { RECETARIOS } from "@/constants/recetas";
import recetaApi from "@/services/receta";
import { useRouter } from "next/router";
import { useState } from "react";
import { useSelector } from "react-redux";

export default function Recetarios() {
  const idUsuario = useSelector((state) => state.user.usuarioActual.id);

  const router = useRouter();
  const [nombre, setNombre] = useState("");
  const handleSubmit = (data) => {
    recetaApi.crearRecetario({ nombre: data, idAutor: idUsuario });
  };
  const handleDelete = (id) => {
    recetaApi.borrarRecetario(id);
  };
  return (
    <div className='flex flex-row'>
      <div className='text-center w-1/2'>
        <div>Crear Recetario</div>
        <form
          className='flex flex-col pl-36 pt-8 items-start'
          onSubmit={(e) => {
            handleSubmit(e.target.value);
          }}
        >
          <label key='nombre'>Nombre:</label>
          <input
            key='nombre'
            type='text'
            placeholder='Nombre...'
            required={true}
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
          />
          <button
            type='submit'
            className='bg-blue-600 rounded-lg text-white my-5 p-2'
          >
            Crear Recetario
          </button>
        </form>
      </div>
      <div className='w-1/2 flex flex-col items-center'>
        <h1>Mis Recetarios</h1>
        <div className='flex flex-col w-full items-center gap-4'>
          {RECETARIOS.map((recetario, index) => {
            return (
              <div
                key={recetario.nombre}
                className='relative shadow-md flex flex-row w-2/3'
              >
                <button
                  onClick={() => router.push(`/recetario/${recetario.id}`)}
                  className=' bg-purple-200 p-2 w-full h-full py-3 rounded-lg flex flex-col'
                >
                  <span>#{index}</span>
                  <span>{recetario.nombre}</span>
                  <span>N° Recetas: {recetario.recetas.length}</span>
                </button>
                <button
                  onClick={() => {
                    handleDelete(recetario.id);
                  }}
                  className='absolute bg-rose-600 z-10 font-semibold text-xs text-white top-4 h-9 right-3 rounded-lg px-1'
                >
                  BORRAR
                </button>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
