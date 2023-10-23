import LoadingWrapper from "@/components/LoadingWrapper";
import { CATEGORIAS } from "@/constants/camposFiltros";
import { borradorApi } from "@/services/borradores";
import { useEffect } from "react";
import { useState } from "react";
import { useFieldArray, useForm } from "react-hook-form";
import cn from "classnames";
import FormularioBorrador from "./componentes/formularioBorrador";
import { useRouter } from "next/router";

export default function Borrador({ idBorrador }) {
  const [borrador, setBorrador] = useState();
  const [recetaSeleccionada, setRecetaSeleccionada] = useState();

  const router = useRouter();

  const agrgarEstado = (recetas) =>
    recetas.reduce(
      (array, receta) =>
        receta.fotos[0]?.url &&
        receta.ingredientes.length > 0 &&
        receta.pasos.length > 0
          ? [...array, { ...receta, completo: true }]
          : [...array, { ...receta, completo: false }],
      []
    );

  useEffect(() => {
    idBorrador &&
      borradorApi
        .traerBorrador(idBorrador)
        .then((response) => setBorrador(agrgarEstado(response.data)));
  }, [idBorrador]);

  const onFormSubmit = (data) => {
    const nuevoBorrador = borrador.reduce(
      (array, receta) =>
        receta.id !== data.id
          ? [...array, receta]
          : data.fotos[0]?.url &&
            data.ingredientes.length > 0 &&
            data.pasos.length > 0
          ? [...array, { ...data, completo: true }]
          : [...array, { ...data, completo: false }],
      []
    );
    borradorApi
      .editarBorrador(idBorrador, nuevoBorrador)
      .then(() =>
        borradorApi
          .traerBorrador(idBorrador)
          .then((response) => setBorrador(agrgarEstado(response.data)))
      );
  };

  const todoCompleto =
    borrador && !borrador.some((receta) => receta.completo === false);

  const crearRecetas = () => {
    borradorApi
      .crearRecetas(idBorrador, borrador)
      .then((response) => response.ok && router.push("/recetas"));
  };

  return (
    <div>
      <LoadingWrapper loading={!borrador}>
        {borrador && (
          <>
            <h1 className='text-center font-bold text-lg'>
              BORRADOR {idBorrador}
            </h1>
            <div className='flex flex-row'>
              <div className='w-1/2'>
                <h2 className='text-center font-bold text-md'>RECETAS</h2>
                <div className='flex flex-col gap-4 items-center'>
                  {borrador.map((receta, index) => {
                    return (
                      <button
                        onClick={() => setRecetaSeleccionada(index)}
                        key={receta.id}
                        className='bg-yellow-500 rounded-xl p-5 shadow-md w-2/3 relative'
                      >
                        <div className='font-semibold'>Receta {index + 1}</div>
                        <div className='font-semibold'>{receta.titulo}</div>
                        <div className='absolute top-0 bottom-0 right-0 m-4 flex flex-col justify-center font-semibold bg-slate-100 px-2 rounded-lg'>
                          <div className='text-center'>Estado:</div>
                          <div
                            className={cn(
                              receta.completo
                                ? "text-green-800"
                                : "text-red-800"
                            )}
                          >
                            {receta.completo ? "completada" : "incompleta"}
                          </div>
                        </div>
                      </button>
                    );
                  })}
                </div>
                <div className='flex flex-row justify-center'>
                  {todoCompleto && (
                    <button
                      onClick={crearRecetas}
                      className='py-4 px-7 w-2/3 mt-7 bg-gradient-to-tr from-blue-700 to-blue-500 text-white font-bold m-auto my-2 rounded-lg'
                    >
                      CREAR RECETAS
                    </button>
                  )}
                </div>
              </div>
              <div className='w-1/2 bg-white border border-gray-300 shadow-2xl m-4 rounded-2xl p-2'>
                <div className='text-center font-bold text-md'>
                  COMPLETA LA RECETA:
                </div>
                {borrador &&
                  borrador.map((receta, index) => {
                    return (
                      <div
                        className={cn(recetaSeleccionada !== index && "hidden")}
                      >
                        <FormularioBorrador
                          receta={receta}
                          onSubmit={onFormSubmit}
                        />
                      </div>
                    );
                  })}
              </div>
            </div>
          </>
        )}
      </LoadingWrapper>
    </div>
  );
}
