import { traerUsuariosSeguidos } from "@/redux/user/actions";
import { interaccionApi } from "@/services/interacciones";
import FeatherIcon from "feather-icons-react/build/FeatherIcon";
import { useDispatch } from "react-redux";
import cn from "classnames";

export default function ListaUsuarios({
  usuarios,
  usuariosSeguidos,
  mostrarUsuariosSeguidos,
  idUsuario,
}) {
  const dispatch = useDispatch();
  const esSeguido = (id) =>
    usuariosSeguidos?.some((usuario) => usuario.id === id);

  const handleSeguirUsuario = (id) => {
    if (esSeguido(id)) {
      interaccionApi
        .dejarDeSeguirUsuario({ idSeguir: id, idSeguidor: idUsuario })
        .then((response) => {
          if (response.ok) {
            dispatch(traerUsuariosSeguidos(idUsuario));
          }
        });
    } else {
      interaccionApi
        .seguirUsuario({ idSeguir: id, idSeguidor: idUsuario })
        .then((response) => {
          if (response.ok) {
            dispatch(traerUsuariosSeguidos(idUsuario));
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
              className='flex flex-ro shadow-md w-64  h-20'
            >
              <button
                onClick={() => handleSeguirUsuario(usuario.id)}
                className='transition duration-300 hover:bg-blue-400 flex flex-col p-1 w-16 h-full pr-1 rounded-l-lg justify-center bg-white items-center'
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
              <button className='transition duration-300 text-left pl-2 bg-gray-100 w-full rounded-r-lg hover:bg-gray-300	'>
                <div>{usuario.nombre}</div>
                <div>@{usuario.usuario}</div>
                <div>{usuario.email}</div>
              </button>
            </div>
          );
        })}
    </div>
  );
}
