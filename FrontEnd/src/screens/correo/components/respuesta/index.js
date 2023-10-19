import { userApi } from "@/services/user";
import { useForm } from "react-hook-form";

export default function CorreoRespuesta({ idCorreo }) {
  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    userApi.responderMensaje({ idMensaje: idCorreo, respuesta: data.mensaje });
  };
  return (
    <div className='ml-8'>
      <form className='flex flex-col gap-2' onSubmit={handleSubmit(onSubmit)}>
        <textarea
          className='p-2 border-2 rounded-lg border-blue-700'
          {...register("mensaje", { required: true })}
          rows={3}
          placeholder='tu respuesta...'
        />
        <button
          type='submit'
          className='bg-green-600 hover:bg-green-700 text-white p-2 rounded-lg font-semibold shadow-xl'
        >
          Responder
        </button>
      </form>
    </div>
  );
}
