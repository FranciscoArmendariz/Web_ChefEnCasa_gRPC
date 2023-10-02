import { USUARIOS } from "@/constants/usuarios";
import ListaUsuarios from "./components/listaUsuarios";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { traerUsuarios, traerUsuariosSeguidos } from "@/redux/user/actions";
import LoadingWrapper from "@/components/LoadingWrapper";
import cn from "classnames";

const selectedStyle = "bg-blue-500 text-white";
const unSelectedStyle = "bg-white text-blue-500 border border-blue-500";

export default function Usuarios() {
  const dispatch = useDispatch();
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);
  const [mostrarSeguidos, setMostrarUsuariosSeguidos] = useState();

  const usuarios = useSelector((state) => state.user.listaUsuarios);

  useEffect(() => {
    dispatch(traerUsuarios());
    if (idUsuario) {
      dispatch(traerUsuariosSeguidos(idUsuario));
    }
  }, [dispatch, idUsuario]);

  const usuariosSeguidos = useSelector(
    (state) => state.user.listaUsuariosSeguidos
  );
  return (
    <LoadingWrapper loading={!usuarios}>
      <div className='max-h-screen flex flex-col mx-10'>
        <div className='my-5'>
          <button
            className={cn("mx-1 p-1 font-semibold rounded-md", {
              [selectedStyle]: !mostrarSeguidos,
              [unSelectedStyle]: mostrarSeguidos,
            })}
            onClick={() => setMostrarUsuariosSeguidos(false)}
          >
            USUARIOS
          </button>
          <button
            className={cn("mx-1 p-1 font-semibold rounded-md", {
              [selectedStyle]: mostrarSeguidos,
              [unSelectedStyle]: !mostrarSeguidos,
            })}
            onClick={() => setMostrarUsuariosSeguidos(true)}
          >
            USUARIOS SEGUIDOS
          </button>
        </div>
        <div className='bg-gray-200 m-auto  h-full w-full p-5 rounded-lg relative '>
          <div className='h-full overflow-y-auto flex justify-center'>
            <ListaUsuarios
              idUsuario={idUsuario}
              usuarios={usuarios}
              usuariosSeguidos={usuariosSeguidos}
              mostrarUsuariosSeguidos={mostrarSeguidos}
            />
          </div>
        </div>
      </div>
    </LoadingWrapper>
  );
}
