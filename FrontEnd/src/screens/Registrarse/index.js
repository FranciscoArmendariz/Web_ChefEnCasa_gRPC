import { crearUsuario, limpiarRespuestas, login } from "@/redux/user/actions";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";

export default function Registrarse() {
  const dispatch = useDispatch();
  const router = useRouter();
  const crearUsuarioRespuesta = useSelector(
    (state) => state.user?.crearUsuarioRespuesta
  );

  const { getValues, register, handleSubmit } = useForm({
    defaultValues: {
      nombre: "",
      usuario: "",
      email: "",
      clave: "",
      rol: "usuario",
    },
  });

  useEffect(() => {
    if (crearUsuarioRespuesta) {
      const aux = getValues();
      dispatch(login({ usuario: aux.usuario, contrasenia: aux.clave }));
      dispatch(limpiarRespuestas());
      router.push("/");
    }
  }, [crearUsuarioRespuesta]);

  const onSubmit = (data) => {
    dispatch(crearUsuario(data));
  };

  return (
    <div className='w-96 flex flex-col items-center m-auto text-center mt-20 bg- p-10 pt-6 pb-6 border border-gray-600 rounded-lg bg-gradient-to-br from-orange-400 to-orange-600'>
      <h1 className='text-white font-bold mb-4'>REGISTRARSE</h1>
      <div>
        <form
          onSubmit={handleSubmit(onSubmit)}
          className='flex flex-col items-start'
        >
          <label htmlFor='nombre'>Nombre</label>
          <input
            className='w-72 border border-gray-600 mb-2 rounded-lg pl-1'
            id='nombre'
            {...register("nombre", { required: true })}
            placeholder='Nombre del perfil'
          />
          <label htmlFor='usuario'>Usuario</label>
          <input
            className='w-72 border border-gray-600 mb-2 rounded-lg pl-1'
            id='usuario'
            {...register("usuario", { required: true })}
            placeholder='Usuario'
          />
          <label htmlFor='email'>Email</label>
          <input
            className='w-72 border border-gray-600 mb-2 rounded-lg pl-1'
            id='email'
            {...register("email", { required: true })}
            placeholder='MailDeEjemplo@Email.com'
            type='email'
          />
          <label htmlFor='clave'>Contraseña</label>
          <input
            className='w-72 border border-gray-600 mb-2 rounded-lg pl-1'
            id='clave'
            {...register("clave", { required: true })}
            placeholder='Contraseña'
            type='password'
          />
          <button
            type='submit'
            className='bg-gradient-to-b from-blue-400 to-blue-500 text-white font-bold border h-10 w-2/3 border-gray-600 rounded-lg m-auto mt-3'
          >
            CREAR CUENTA
          </button>
        </form>
      </div>
    </div>
  );
}
