import FormularioReceta from "@/components/formularioReceta";
import { crearReceta, limpiarRespuestas } from "@/redux/recetas/actions";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

export default function NuevaReceta() {
  const idUsuario = useSelector((state) => state.user.usuarioActual?.id);
  const nombreUsuario = useSelector(
    (state) => state.user.usuarioActual?.nombre
  );
  const crearRecetaRespuesta = useSelector(
    (state) => state.recetas.crearRecetaRespuesta
  );
  const router = useRouter();
  const dispatch = useDispatch();

  const onSubmit = (data) => {
    dispatch(
      crearReceta({
        receta: {
          ...data,
          idUsuario,
          fotos: undefined,
          pasos: undefined,
          ingredientes: undefined,
        },
        nombreAutor: nombreUsuario,
        fotos: data.fotos,
        ingredientes: data.ingredientes,
        pasos: data.pasos,
      })
    );
  };

  useEffect(() => {
    if (crearRecetaRespuesta) {
      dispatch(limpiarRespuestas());
      router.push("/");
    }
  }, [crearRecetaRespuesta]);

  return (
    <div className='px-10 flex flex-col items-center'>
      <h1 className='font-bold text-lg my-3'>CREAR RECETA</h1>
      <div className='w-full'>
        <FormularioReceta onSubmit={onSubmit} />
      </div>
    </div>
  );
}
