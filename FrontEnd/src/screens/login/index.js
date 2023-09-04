import { login } from "@/redux/user/actions";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";

export default function Login() {
  const { register, handleSubmit, getValues } = useForm({
    defaultValues: { usuario: "", contrasenia: "" },
  });
  const router = useRouter();
  const dispach = useDispatch();
  const loginSucces = useSelector((state) => state.user.isLogged);
  const loginFailure = useSelector((state) => state.user.loginError);
  useEffect(() => {
    if (loginSucces) {
      const campos = getValues();
      localStorage.setItem(
        "loginData",
        `${campos.usuario}_${campos.contrasenia}`
      );
      router.push("/");
    }
  }, [loginSucces]);

  const onSubmit = (data) => {
    dispach(login({ usuario: data.usuario, contrasenia: data.contrasenia }));
  };

  return (
    <div className='w-96 flex flex-col items-center m-auto text-center mt-20 bg- p-10 pt-6 border border-gray-600 rounded-lg bg-gradient-to-br from-orange-400 to-orange-600'>
      <h2 className='text-white font-bold mb-4'>INICIÁ SESIÓN</h2>
      <form
        className='flex flex-col w-64 gap-4 items-center'
        onSubmit={handleSubmit(onSubmit)}
      >
        <input
          className=' border-gray-600 border rounded-lg pl-3'
          {...register("usuario", {
            required: "Nombre de usuario obligatorio",
          })}
          placeholder='usuario...'
        />
        <input
          className=' border-gray-600 border rounded-lg pl-3'
          type='password'
          {...register("contrasenia", { required: "Contraseña obligatoria" })}
          placeholder='contraseña...'
        />
        {loginFailure && (
          <div className=' rounded-md border-2 text-red-800 bg-red-100 p-1 font-semibold border-red-800'>
            Usuario o Contraseña Incorrectos
          </div>
        )}
        <button
          type='submit'
          className='bg-gradient-to-b from-blue-400 to-blue-500 text-white font-bold border h-10 w-2/3 border-gray-600 rounded-lg'
        >
          INICIAR SESION
        </button>
        <button
          type='button'
          className='bg-background font-bold border h-10 w-2/3 border-gray-600 rounded-lg'
        >
          REGISTRARME
        </button>
      </form>
    </div>
  );
}
