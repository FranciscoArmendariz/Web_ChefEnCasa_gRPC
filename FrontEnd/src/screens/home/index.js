import LoadingWrapper from "@/components/LoadingWrapper";
import {
  traerRecetasConFiltros,
  traerRecetasNuevas,
} from "@/redux/recetas/actions";
import { traerUsuarios } from "@/redux/user/actions";
import Image from "next/image";
import { useState } from "react";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Home() {
  const dispatch = useDispatch();
  const [ultimasRecetas, setUltimasRecetas] = useState(null);
  useEffect(() => {
    dispatch(traerUsuarios());
    dispatch(traerRecetasNuevas());
    dispatch(
      traerRecetasConFiltros({
        titulo: "",
        categoria: "",
        page: 1,
        size: 50,
        orderBy: "asc",
        sortBy: "id",
      })
    );
  }, []);

  const usuarios = useSelector((state) => state.user.listaUsuarios);
  const recetas = useSelector((state) => state.recetas.listaRecetas);

  console.log(
    "user",
    recetas?.sort((a, b) => (a.promedio >= b.promedio ? -1 : 1)).slice(0, 5)
  );
  const recetasNuevas = useSelector(
    (state) => state.recetas.listaRecetasNuevas
  );

  useEffect(() => {
    if (recetasNuevas) {
      const recetasNuevasJson = recetasNuevas.reduce(
        (array, receta) => [...array, JSON.parse(receta)],

        []
      );

      const recetasNuevasLocal = localStorage
        .getItem("ultimasRecetas")
        ?.split("|")
        .reduce(
          (array, receta) => [...array, JSON.parse(receta)],

          []
        );

      const ultimasCincoRecetas = (
        recetasNuevasJson
          ? recetasNuevasLocal
            ? [
                ...recetasNuevasJson
                  .toReversed()
                  .reduce(
                    (array, receta) =>
                      recetasNuevasLocal.some(
                        (local) =>
                          local.tituloReceta === receta.tituloReceta &&
                          local.foto === receta.foto
                      )
                        ? array
                        : [...array, receta],
                    []
                  ),
                ...recetasNuevasLocal,
              ]
            : [...recetasNuevasJson.toReversed()]
          : recetasNuevasLocal
          ? [...recetasNuevasLocal]
          : []
      ).slice(0, 5);

      localStorage.removeItem("ultimasRecetas");
      if (ultimasCincoRecetas.length > 0) {
        localStorage.setItem(
          "ultimasRecetas",
          ultimasCincoRecetas.reduce(
            (string, receta) =>
              !!string
                ? `${string}|${JSON.stringify(receta)}`
                : JSON.stringify(receta),
            ""
          )
        );
      }
      setUltimasRecetas(ultimasCincoRecetas);
    }
  }, [recetasNuevas]);

  return (
    <div className='pt-8 px-14 flex flex-col justify-center'>
      <h1 className='flex-1 font-bold text-5xl text-center'>CHEF EN CASA</h1>
      <div className='flex'>
        <div className='w-1/4'>
          <h2 className='font-bold text-2xl text-center pt-4 pb-4'>
            TOP USUARIOS
          </h2>
          <LoadingWrapper loading={!usuarios}>
            <div className='flex flex-col items-center gap-2'>
              {usuarios
                ?.sort((a, b) => (a.puntaje >= b.puntaje ? -1 : 1))
                .slice(0, 5)
                .map((usuario) => {
                  return (
                    <div
                      key={usuario.nombre}
                      className='h-20 flex flex-col items-start w-2/3 bg-blue-200 rounded-lg p-2 justify-center shadow-lg'
                    >
                      <div className='font-bold'>{usuario.nombre}</div>
                      <div>
                        <span className='font-semibold'>@</span>
                        {usuario.usuario}
                      </div>
                      <div>
                        <span className='font-bold'>{usuario.puntaje}</span>{" "}
                        punto/s
                      </div>
                    </div>
                  );
                })}
            </div>
          </LoadingWrapper>
        </div>
        <div className='flex-1'>
          <h2 className='font-bold text-2xl text-center pt-4 pb-4'>
            TOP RECETAS
          </h2>
          <LoadingWrapper loading={!recetas}>
            <div className='max-w-6xl m-auto pb-3 flex flex-row flex-wrap justify-center gap-2'>
              {recetas
                ?.sort((a, b) => (a.promedio >= b.promedio ? -1 : 1))
                .slice(0, 5)
                .map((receta) => {
                  return (
                    <div
                      key={receta.titulo}
                      className='flex flex-row border border-gray-300 rounded-lg relative w-56 shadow-lg'
                    >
                      <div className='flex w-full flex-col relative h-40'>
                        {receta?.fotos[0] && (
                          <Image
                            src={receta.fotos[0].url}
                            alt={receta.tituloReceta}
                            className='rounded-lg'
                            style={{ objectFit: "cover" }}
                            fill
                          />
                        )}
                        <div className='absolute w-full pr-10 pl-3  bottom-0 left-0 bg-white/80 rounded-lg '>
                          <div className='font-semibold'>{receta.titulo}</div>
                          Valoracion:{" "}
                          <span className='font-semibold'>
                            {receta.promedio}
                          </span>
                        </div>
                      </div>
                    </div>
                  );
                })}
            </div>
          </LoadingWrapper>
        </div>
        <div className='flex-1'>
          <h2 className='font-bold text-2xl text-center pt-4 pb-4'>
            RECETAS NUEVAS
          </h2>
          <LoadingWrapper loading={!ultimasRecetas}>
            <div className='max-w-6xl m-auto pb-5 flex flex-row flex-wrap justify-center gap-2'>
              {ultimasRecetas?.map((receta) => {
                return (
                  <div
                    key={receta.titulo}
                    className='flex flex-row border border-gray-300 rounded-lg relative w-56 shadow-lg'
                  >
                    <div className='flex w-full flex-col relative h-40'>
                      {receta?.foto && (
                        <Image
                          src={receta.foto}
                          alt={receta.tituloReceta}
                          className='rounded-lg'
                          style={{ objectFit: "cover" }}
                          fill
                        />
                      )}
                      <div className='absolute w-full pr-10 pl-3  bottom-0 left-0 bg-white/80 rounded-lg '>
                        <div className='font-semibold'>
                          {receta.tituloReceta}
                        </div>
                        Autor: {receta.nombreDeAutor}
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </LoadingWrapper>
        </div>
      </div>
    </div>
  );
}
