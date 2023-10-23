import LoadingWrapper from "@/components/LoadingWrapper";
import { RECETARIOS } from "@/constants/recetas";
import recetaApi from "@/services/receta";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useSelector } from "react-redux";

export default function Recetarios() {
  const idUsuario = useSelector((state) => state.user.usuarioActual.id);
  const router = useRouter();
  const [recetarios, setRecetarios] = useState();
  const { register, handleSubmit } = useForm();

  useEffect(() => {
    idUsuario &&
      recetaApi
        .traerRecetarios(idUsuario)
        .then((response) => setRecetarios(RECETARIOS));
  }, [idUsuario]);

  const onSubmit = (data) => {
    recetaApi
      .crearRecetario({ nombre: data.nombre, idAutor: idUsuario })
      .then(() =>
        recetaApi
          .traerRecetarios(idUsuario)
          .then((response) => setRecetarios(RECETARIOS))
      );
  };
  const handleDelete = (id) => {
    recetaApi
      .borrarRecetario(id)
      .then(() =>
        recetaApi
          .traerRecetarios(idUsuario)
          .then((response) => setRecetarios(RECETARIOS))
      );
  };
  return (
    <div className='flex flex-row m-10 mt-0'>
      <div className='flex flex-col items-center bg-slate-50 rounded-3xl shadow-xl p-3 mt-7 w-1/2 h-min'>
        <div className='font-semibold text-lg pt-4'>Crear Recetario</div>
        <form
          className='flex flex-col pt-8 w-full items-start'
          onSubmit={handleSubmit(onSubmit)}
        >
          <label htmlFor='nombre' className='font-medium'>
            Nombre:
          </label>
          <input
            id='nombre'
            type='text'
            placeholder='Nombre del nuevo recetario...'
            className='bg-orange-50 w-full pl-1 focus:bg-orange-100 border border-orange-200 rounded-lg outline-orange-300'
            {...register("nombre", { required: true })}
          />
          <button
            type='submit'
            className='bg-blue-600 rounded-lg text-white my-5 p-2 self-center'
          >
            Crear Recetario
          </button>
        </form>
      </div>
      <div className='w-1/2 flex flex-col items-center'>
        <h1 className='font-semibold text-lg py-7'>Mis Recetarios</h1>
        <div className='flex flex-col w-full items-center gap-4'>
          <LoadingWrapper loading={!recetarios}>
            {recetarios &&
              recetarios.map((recetario, index) => {
                return (
                  <div
                    key={recetario.nombre}
                    className='relative shadow-md flex flex-row w-2/3'
                  >
                    <button
                      onClick={() => router.push(`/recetario/${recetario.id}`)}
                      className=' bg-purple-200 p-2 w-full h-full py-3 rounded-lg flex flex-col'
                    >
                      <span>#{index + 1}</span>
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
          </LoadingWrapper>
        </div>
      </div>
    </div>
  );
}
