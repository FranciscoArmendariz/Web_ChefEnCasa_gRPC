import LoadingWrapper from "@/components/LoadingWrapper";
import { limpiarListas, traerRecetaPorId } from "@/redux/recetas/actions";
import cn from "classnames";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import Image from "next/image";
import { useState } from "react";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Receta({ idReceta }) {
  const [toggle, setToggle] = useState(false);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(limpiarListas());
    if (!!idReceta) {
      dispatch(traerRecetaPorId(idReceta));
    }
  }, [dispatch, idReceta]);

  const handlePuntuacion = (puntaje) => {};

  const receta = useSelector((state) => state.recetas.recetaPorId);
  /*const valoracion = [
    receta?.promedio > 0,
    receta?.promedio > 1,
    receta?.promedio > 2,
    receta?.promedio > 3,
    receta?.promedio > 4,
  ];*/
  const valoracion = [true, true, true, false, false];
  const estrellas = [
    { className: "peer/1", puntos: 1 },
    { className: "peer/2 peer-hover/1:fill-transparent", puntos: 2 },
    {
      className:
        "peer/3 peer-hover/1:fill-transparent peer-hover/2:fill-transparent",
      puntos: 3,
    },
    {
      className:
        "peer/4 peer-hover/1:fill-transparent peer-hover/2:fill-transparent peer-hover/3:fill-transparent",
      puntos: 4,
    },
    {
      className:
        "peer-hover/1:fill-transparent peer-hover/2:fill-transparent peer-hover/3:fill-transparent peer-hover/4:fill-transparent",
      puntos: 5,
    },
  ];
  return (
    <LoadingWrapper loading={!receta}>
      <div className='mx-6 relative'>
        <div className='flex flex-row pt-5'>
          <h1 className='font-bold text-2xl mr-3'>{receta?.titulo}</h1>
          <div className='bg-orange-500 text-white w-9 h-9 relative bottom-2 shadow-xl rounded-lg rounded-bl-sm flex justify-center text-center items-center font-semibold'>
            {receta?.tiempoAprox}´
          </div>
        </div>
        <div className='pb-4'>
          <span className='uppercase font-medium'>{receta?.categoria}</span> -{" "}
          {receta?.descripcion}
        </div>
        <div className='flex flex-row'>
          <div className='flex-1'>
            <div className='mb-5'>
              <span className='font-semibold'>INGREDIENTES</span>
              <ul className='list-none pl-4'>
                {receta?.ingredientes?.map((ingrediente) => {
                  return (
                    <li key={`${ingrediente.id}-${ingrediente.nombre}`}>
                      {ingrediente.nombre}: {ingrediente.cantidad}
                    </li>
                  );
                })}
              </ul>
            </div>
            <div className='mb-5'>
              <span className='font-semibold'>PASOS</span>
              <ul className='list-decimal pl-9'>
                {receta?.pasos?.map((paso) => {
                  return (
                    <li key={`${paso.id}-${paso.numero}`}>
                      {paso.descripcion}
                    </li>
                  );
                })}
              </ul>
            </div>
            <div>
              <span className='font-semibold'>IMAGENES</span>
              <div className='pl-6 flex flex-row gap-3'>
                {receta?.fotos?.map((foto) => {
                  return (
                    foto?.url && (
                      <Image
                        key={foto?.url}
                        src={foto?.url || "/"}
                        alt='imagen'
                        className='rounded-lg'
                        style={{ objectFit: "cover" }}
                        width={150}
                        height={150}
                      />
                    )
                  );
                })}
              </div>
            </div>
          </div>
          <div className='flex-1'>
            <div className='mb-5 flex flex-col items-center'>
              <span className='font-semibold'>VALORACIÓN</span>
              <div className='flex flex-row'>
                {valoracion.map((filled) => {
                  return (
                    <FeatherIcon
                      icon={"star"}
                      className={`stroke-1 ${filled && "fill-yellow-400"}`}
                    />
                  );
                })}
              </div>
            </div>
            <div className='mb-5 flex flex-col items-center '>
              <span className='font-semibold'>COMENTARIOS</span>
              <form className='flex flex-col items-center bg-blue-200 w-1/2 p-3 rounded-xl shadow-xl z-10'>
                <textarea className='w-full rounded-lg' />
                <button className='relative bottom-1 -z-10 bg-blue-500 hover:bg-blue-400 transition-colors duration-300 font-semibold py-1 text-white w-full rounded-b-lg'>
                  Agregar comentario
                </button>
              </form>
            </div>
            <div className='mb-5 flex flex-col items-center'>
              <button
                onClick={() => setToggle(true)}
                className='font-semibold shadow-2xl border border-slate-500 rounded-lg p-3 bg-gradient-to-br from-orange-500 to-orange-700 hover:from-orange-400 hover:to-orange-600 text-white'
              >
                VALORÁ LA RECETA
              </button>
              <div
                className={cn(
                  "relative w-48 h-16 bg-white top-1 border-gray-500 border rounded-lg flex justify-center items-center shadow-2xl",
                  { hidden: !toggle }
                )}
              >
                <button
                  className='absolute top-1 right-1 p-1'
                  onClick={() => setToggle(false)}
                >
                  <FeatherIcon icon='x' size={15} />
                </button>
                <div className='group flex'>
                  {estrellas.map((estrlla) => {
                    return (
                      <button
                        key={estrlla.puntos}
                        className={cn(
                          "fill-transparent group-hover:fill-yellow-500",
                          estrlla.className
                        )}
                      >
                        <FeatherIcon
                          icon={"star"}
                          className='stroke-1 fill-inherit'
                        />
                      </button>
                    );
                  })}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </LoadingWrapper>
  );
}
