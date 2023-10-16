import { CORREOS_ENVIADOS, CORREOS_RECIBIDOS } from "@/constants/correos";
import { useEffect, useState } from "react";
import cn from "classnames";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { useDispatch, useSelector } from "react-redux";
import { traerUsuarios } from "@/redux/user/actions";
import { userApi } from "@/services/user";
import CorreoRespuesta from "./components/respuesta";

export default function Correo() {
  const dispatch = useDispatch();
  const correosEnviados = CORREOS_ENVIADOS;
  const correosRecibidos = CORREOS_RECIBIDOS;
  const [toggleNuevo, setToggleNuevo] = useState(false);

  const [correoReceptor, setCorreoReceptor] = useState("");
  const [correoAsunto, setCorreoAsunto] = useState("");
  const [correoMensaje, setCorreoMensaje] = useState("");

  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);

  useEffect(() => {
    dispatch(traerUsuarios());
  }, []);

  const usuarios = useSelector((state) => state.user.listaUsuarios);

  const handleEnviarCorreo = () => {
    userApi.crearMensaje({
      asunto: correoAsunto,
      idAutor: idUsuario,
      idReceptor: correoReceptor,
      mensaje: correoMensaje,
    });
  };

  return (
    <div className='p-3 flex flex-col items-center h-full'>
      <h1 className='font-bold text-3xl'>CORREO</h1>
      <div className='px-5 py-1 relative w-full flex flex-col items-center'>
        <button
          onClick={() => setToggleNuevo(!toggleNuevo)}
          className='bg-green-600 hover:bg-green-700 text-white p-2 rounded-lg font-semibold shadow-xl'
        >
          NUEVO MENSAJE
        </button>
        <div
          className={cn(
            "absolute top-12 w-1/2 p-4 pt-6 bg-white z-10 border-gray-500 border rounded-lg flex justify-center items-center shadow-2xl",
            { hidden: !toggleNuevo }
          )}
        >
          <button
            className='absolute top-1 right-1 p-1'
            onClick={() => setToggleNuevo(false)}
          >
            <FeatherIcon icon='x' size={15} />
          </button>
          <form
            onSubmit={() => {
              handleEnviarCorreo();
            }}
            className='flex flex-col gap-2 w-full'
          >
            <div className='flex flex-col'>
              <label className='font-semibold'>PARA:</label>
              <select
                required
                name='receptor'
                className='shadow-sm rounded-lg bg-blue-100 p-1 outline-blue-700'
                value={correoReceptor}
                onChange={(e) => setCorreoReceptor(e.target.value)}
              >
                {usuarios?.map((usuario) => {
                  return <option value={usuario.id}>{usuario.nombre}</option>;
                })}
              </select>
            </div>
            <div className='flex flex-col'>
              <label className='font-semibold'>ASUNTO:</label>
              <input
                required
                name='asunto'
                className='shadow-sm rounded-md p-1 bg-blue-100 outline-blue-700'
                value={correoAsunto}
                onChange={(e) => setCorreoAsunto(e.target.value)}
              />
            </div>
            <div className='flex flex-col'>
              <label className='font-semibold'>MENSAJE:</label>
              <textarea
                required
                name='mensaje'
                className='shadow-sm rounded-lg bg-blue-100 p-1 outline-blue-700'
                rows={4}
                value={correoMensaje}
                onChange={(e) => {
                  setCorreoMensaje(e.target.value);
                }}
              />
            </div>
            <button
              type='submit'
              className='bg-blue-700 hover:bg-blue-600 text-white p-2 rounded-lg font-semibold shadow-xl'
            >
              ENVIAR
            </button>
          </form>
        </div>
      </div>
      <div className='flex flex-row w-full'>
        <div className='flex w-1/2 flex-col items-center px-5'>
          <h2 className='font-semibold text-xl'>MENSAJES ENVIADOS</h2>
          <div className='bg-blue-100 mb-5 border rounded-lg w-full p-4 mt-3 flex gap-5 flex-col shadow-lg'>
            {correosEnviados.map((correo) => {
              return (
                <div className='flex flex-col border rounded-lg border-gray-500 bg-white p-2'>
                  <div>
                    <div>
                      <span className='font-semibold'>PARA: </span>
                      {correo.receptor.nombre}
                    </div>
                    <div>
                      <span className='font-semibold'>ASUNTO: </span>
                      {correo.asunto}
                    </div>
                  </div>
                  <div className='flex flex-col gap-2 pt-2'>
                    <div className='bg-blue-600 text-white ml-8 shadow-lg border py-1 px-2 rounded-lg'>
                      {correo.mensaje}
                    </div>
                    {correo?.respuesta && (
                      <div className='bg-blue-200 mr-8 shadow-lg border py-1 px-2 rounded-lg'>
                        {correo.respuesta}
                      </div>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        </div>
        <div className='flex w-1/2 flex-col items-center'>
          <h2 className='font-semibold text-xl text-center'>
            MENSAJES RECIBIDOS
          </h2>
          <div className='bg-blue-100 mb-5 border rounded-lg w-full p-4 mt-3 flex gap-5 flex-col shadow-lg'>
            {correosRecibidos.map((correo) => {
              return (
                <div className='flex flex-col border rounded-lg border-gray-500 bg-white p-2'>
                  <div>
                    <div>
                      <span className='font-semibold'>DE: </span>
                      {correo.autor.nombre}
                    </div>
                    <div>
                      <span className='font-semibold'>ASUNTO: </span>
                      {correo.asunto}
                    </div>
                  </div>
                  <div className='flex flex-col gap-2 pt-2'>
                    <div className='bg-blue-200 mr-8 shadow-lg border py-1 px-2 rounded-lg'>
                      {correo.mensaje}
                    </div>
                    {correo?.respuesta ? (
                      <div className='bg-blue-600 text-white ml-8 shadow-lg border py-1 px-2 rounded-lg'>
                        {correo.respuesta}
                      </div>
                    ) : (
                      <CorreoRespuesta idCorreo={correo.id} />
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
}
