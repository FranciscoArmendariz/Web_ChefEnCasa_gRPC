import { traerUsuarios, traerUsuariosSeguidos } from "@/redux/user/actions";
import { interaccionApi } from "@/services/interacciones";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { useDispatch } from "react-redux";
import cn from "classnames";
import { useRouter } from "next/router";
import { useState } from "react";

export default function ListaUsuarios({
  usuarios,
  usuariosSeguidos,
  mostrarUsuariosSeguidos,
  idUsuario,
}) {
  const router = useRouter();
  const dispatch = useDispatch();
  const esSeguido = (id) =>
    usuariosSeguidos?.some((usuario) => usuario.id === id);
  const [isLoading, setIsLoading] = useState(false);

  const handleSeguirUsuario = (id) => {
    setIsLoading(true);
    if (esSeguido(id)) {
      interaccionApi
        .dejarDeSeguirUsuario({ idSeguir: id, idSeguidor: idUsuario })
        .then((response) => {
          if (response.ok) {
            setIsLoading(false);
            dispatch(traerUsuariosSeguidos(idUsuario));
            dispatch(traerUsuarios());
          }
        });
    } else {
      interaccionApi
        .seguirUsuario({ idSeguir: id, idSeguidor: idUsuario })
        .then((response) => {
          if (response.ok) {
            setIsLoading(false);
            dispatch(traerUsuariosSeguidos(idUsuario));
            dispatch(traerUsuarios());
          }
        });
    }
  };
  return (
    <div className='grid grid-cols-3 w-max items-center max-h-full gap-x-4 gap-y-2'>
      {(mostrarUsuariosSeguidos ? usuariosSeguidos : usuarios)
        ?.filter((usuario) => usuario.id !== idUsuario)
        .map((usuario) => {
          const seguido = esSeguido(usuario.id);
          return (
            <div
              key={`${usuario.id}-${usuario.nombre}`}
              className='flex flex-ro shadow-md w-96 max-w-5xl  h-24'
            >
              <button
                disabled={isLoading}
                onClick={() => handleSeguirUsuario(usuario.id)}
                className='transition duration-300 hover:bg-blue-400 flex flex-col p-1 w-16 h-full pr-1 rounded-l-lg justify-center bg-white items-center disabled:bg-slate-200'
              >
                <FeatherIcon
                  size={35}
                  icon={seguido ? "user-x" : "user-plus"}
                  className={cn(" stroke-1 relative left-1", {
                    "stroke-blue-700": !seguido,
                    "stroke-red-600": seguido,
                  })}
                />
                <div className=' text-sm font-thin'>seguir</div>
              </button>
              <button
                onClick={() =>
                  router.push(
                    `/usuarios/recetas/${usuario.id}-${usuario.nombre}-false`
                  )
                }
                className='transition duration-300 text-left pl-2 bg-gray-100 w-full max-w-lg rounded-r-lg hover:bg-gray-300	'
              >
                <div>{usuario.nombre}</div>
                <div>@{usuario.usuario}</div>
                <div className='truncate'>{usuario.email}</div>
                <div>Puntaje: {usuario.puntaje}</div>
              </button>
            </div>
          );
        })}
    </div>
  );
}
