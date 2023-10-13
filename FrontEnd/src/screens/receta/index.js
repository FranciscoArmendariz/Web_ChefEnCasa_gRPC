import LoadingWrapper from "@/components/LoadingWrapper";
import { RECETARIOS } from "@/constants/recetas";
import {
  limpiarListas,
  nuevoComentario,
  traerRecetaPorId,
  traerRecetasPorUsuario,
} from "@/redux/recetas/actions";
import recetaApi from "@/services/receta";
import cn from "classnames";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import Image from "next/image";
import { useState } from "react";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";

export default function Receta({ idReceta }) {
  const [toggle, setToggle] = useState(false);
  const [toggleRecetario, setToggleRecetario] = useState(false);
  const dispatch = useDispatch();
  const idUsuario = useSelector((state) => state.user?.usuarioActual?.id);

  const recetarios = RECETARIOS;

  const handleAgregarRecetario = (idRecetario) => {
    recetaApi.agregarRecetaRecetario(idRecetario, idReceta);
    setToggleRecetario(false);
  };

  useEffect(() => {
    dispatch(limpiarListas());
    if (!!idReceta) {
      dispatch(traerRecetaPorId(idReceta));
    }
    if (idUsuario) {
      dispatch(traerRecetasPorUsuario(idUsuario));
    }
  }, [dispatch, idReceta, idUsuario]);

  const {
    register: registroUsuario,
    handleSubmit: submitComentario,
    reset: resetComentario,
  } = useForm();

  const receta = useSelector((state) => state.recetas.recetaPorId);
  const recetasUsuario = useSelector(
    (state) => state.recetas.listaRecetasPorUsuario
  );

  const handleSubmitComentario = (data) => {
    const mensaje = {
      idRedactor: idUsuario,
      idReceta: idReceta,
      comentario: data.comentario,
      esAutor: recetasUsuario.some((receta) => receta.id === idReceta),
    };

    dispatch(
      nuevoComentario({
        idRedactor: mensaje.idRedactor,
        idReceta: mensaje.idReceta,
        comentario: mensaje.comentario,
        esAutor: mensaje.esAutor,
      })
    );
    resetComentario();
    dispatch(traerRecetaPorId(idReceta));
  };

  const handlePuntuacion = (puntaje) => {
    recetaApi
      .calificarReceta(idReceta, puntaje)
      .then(dispatch(traerRecetaPorId(idReceta)));
    setToggle(false);
  };

  const valoracion = [
    receta?.promedio > 0,
    receta?.promedio > 1,
    receta?.promedio > 2,
    receta?.promedio > 3,
    receta?.promedio > 4,
  ];
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
                {valoracion.map((filled, index) => {
                  return (
                    <FeatherIcon
                      key={`${index}-${filled}`}
                      icon={"star"}
                      className={`stroke-1 ${filled && "fill-yellow-400"}`}
                    />
                  );
                })}
              </div>
            </div>
            <div className='mb-5 flex flex-col items-center gap-1'>
              <span className='font-semibold'>COMENTARIOS</span>
              <div className='max-h-52 gap-1 flex flex-col overflow-auto w-1/2'>
                {receta?.comentarios.map((comentario) => {
                  return (
                    <div
                      key={comentario.comentario_}
                      className='flex flex-col items-center bg-blue-200  p-3 rounded-xl shadow-xl'
                    >
                      <span className='w-full rounded-lg bg-white p-1'>
                        {comentario.comentario_}
                      </span>
                    </div>
                  );
                })}
              </div>
              <form
                onSubmit={submitComentario(handleSubmitComentario)}
                className='flex flex-col items-center bg-blue-200 w-1/2 p-3 rounded-xl shadow-xl z-10'
              >
                <textarea
                  placeholder='comentario...'
                  {...registroUsuario("comentario", { required: true })}
                  className='w-full rounded-lg'
                />
                <button
                  type='submit'
                  className='relative bottom-1 -z-10 bg-blue-500 hover:bg-blue-400 transition-colors duration-300 font-semibold py-1 text-white w-full rounded-b-lg'
                >
                  Agregar comentario
                </button>
              </form>
            </div>
            {!recetasUsuario?.some((receta) => receta.id === idReceta) && (
              <div className='mb-5 flex flex-col items-center relative'>
                <button
                  onClick={() => setToggle(true)}
                  className='font-semibold shadow-2xl border border-slate-500 rounded-lg p-3 bg-gradient-to-br from-orange-500 to-orange-700 hover:from-orange-400 hover:to-orange-600 text-white'
                >
                  VALORÁ LA RECETA
                </button>
                <div
                  className={cn(
                    "absolute top-14 w-48 h-16 bg-white z-10 border-gray-500 border rounded-lg flex justify-center items-center shadow-2xl",
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
                          onClick={() => handlePuntuacion(estrlla.puntos)}
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
            )}
            <div className='mb-5 flex flex-col items-center relative pt-3'>
              <button
                onClick={() => setToggleRecetario(true)}
                className='font-semibold shadow-2xl border border-slate-500 rounded-lg p-3 bg-gradient-to-br from-orange-500 to-orange-700 hover:from-orange-400 hover:to-orange-600 text-white'
              >
                AGREGAR A RECETARIO
              </button>
              <div
                className={cn(
                  "absolute top-16 w-[300px] h-32 bg-white border-gray-500 border rounded-lg flex justify-center items-center shadow-2xl",
                  { hidden: !toggleRecetario }
                )}
              >
                <button
                  className='absolute top-1 right-0.5 p-1'
                  onClick={() => setToggleRecetario(false)}
                >
                  <FeatherIcon icon='x' size={15} />
                </button>
                <div className='flex flex-col h-full w-full mr-6 gap-2 overflow-auto p-3'>
                  {recetarios.map((recetario) => {
                    return (
                      <button
                        onClick={() => handleAgregarRecetario(recetario.id)}
                        className='bg-blue-500 p-1 rounded-lg text-white'
                        key={recetario.nombre}
                      >
                        {recetario.nombre}
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
