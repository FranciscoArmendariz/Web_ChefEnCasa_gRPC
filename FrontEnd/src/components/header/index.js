import { logout } from "@/redux/user/actions";
import cn from "classnames";
import { useRouter } from "next/router";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function Header({ hideButtons }) {
  const router = useRouter();
  const [mostrarOpciones, setMostrarOpciones] = useState(false);
  const usuario = useSelector((state) => state.user.usuarioActual);
  const dispach = useDispatch();
  const goToPage = (url) => {
    router.push(url);
  };

  const cerrarSesion = () => {
    goToPage("/login");
    dispach(logout());
    localStorage.removeItem("loginData");
  };

  return (
    <div
      className={cn(
        "w-full h-20  bg-gradient-to-br from-orange-500 to-orange-700 flex flex-row items-center",
        { "justify-between": !hideButtons, "justify-end": hideButtons }
      )}
    >
      {!hideButtons && (
        <div className='ml-8'>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2'
            onClick={() => {
              goToPage("/");
            }}
          >
            HOME
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2'
            onClick={() => {
              goToPage("/recetas");
            }}
          >
            RECETAS
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2'
            onClick={() => {
              goToPage("/nuevaReceta");
            }}
          >
            CREAR RECETA
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
            onClick={() => {
              goToPage("/favoritos");
            }}
          >
            FAVORITOS
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
            onClick={() => {
              goToPage("/usuarios");
            }}
          >
            USUARIOS
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
            onClick={() => {
              goToPage("/borradores");
            }}
          >
            BORRADORES
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
            onClick={() => {
              goToPage("/recetarios");
            }}
          >
            RECETARIOS
          </button>
          <button
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
            onClick={() => {
              goToPage("/correo");
            }}
          >
            CORREO
          </button>
          {usuario?.rol === "Admin" && (
            <button
              className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
              onClick={() => {
                goToPage("/denuncias");
              }}
            >
              DENUNCIAS
            </button>
          )}
        </div>
      )}
      {!hideButtons && (
        <div className='mr-11 relative'>
          <button
            onClick={() => {
              setMostrarOpciones(!mostrarOpciones);
            }}
            className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
          >
            MI USUARIO
          </button>
          <div
            className={cn(
              "absolute bg-gray-50 border border-gray-600 rounded-xl px-6 py-4 w-56 top-12 -left-16 z-10",
              { hidden: !mostrarOpciones }
            )}
          >
            <div className='font-semibold mb-3'>usuario: {usuario?.nombre}</div>

            <button
              className='bg-orange-500 text-white font-semibold p-1 w-full mb-2 rounded-lg'
              onClick={() =>
                goToPage(
                  `/usuarios/recetas/${usuario?.id}-${usuario?.nombre}-true`
                )
              }
            >
              MIS RECETAS
            </button>

            <button
              className='bg-red-600 text-white p-1 w-full rounded-lg font-bold'
              onClick={cerrarSesion}
            >
              CERRAR SESION
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
