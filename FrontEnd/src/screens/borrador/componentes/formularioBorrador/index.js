import { useFieldArray, useForm } from "react-hook-form";

export default function FormularioBorrador({ receta, onSubmit }) {
  const { control, register, handleSubmit, getValues } = useForm({
    defaultValues: {
      ...receta,
      fotos: [
        { url: receta.fotos[0]?.url },
        { url: receta.fotos[1]?.url },
        { url: receta.fotos[2]?.url },
        { url: receta.fotos[3]?.url },
        { url: receta.fotos[4]?.url },
      ],
    },
  });

  const { fields: fotosFields } = useFieldArray({ control, name: "fotos" });
  const {
    fields: ingredientesFields,
    remove: ingredientesRemove,
    append: ingredientesAppend,
  } = useFieldArray({ control, name: "ingredientes" });
  const {
    fields: pasosFields,
    remove: pasosRemove,
    append: pasosAppend,
  } = useFieldArray({ control, name: "pasos" });

  return (
    <div>
      <form className='flex flex-col p-3' onSubmit={handleSubmit(onSubmit)}>
        <label htmlFor='titulo'>Titulo:</label>
        <input
          className='bg-white font-semibold'
          id='titulo'
          {...register("titulo", { required: true })}
          disabled
        />
        <label htmlFor='descripcion'>descripción:</label>
        <input
          className='bg-white font-semibold'
          id='descripcion'
          {...register("descripcion", { required: true })}
          disabled
        />
        <label htmlFor='categoria'>Categoria:</label>
        <input
          className='bg-white font-semibold'
          id='categoria'
          {...register("categoria", { required: true })}
          disabled
        />
        <label htmlFor='tiempoAprox'>Tiempo de preparación:</label>
        <input
          className='bg-white font-semibold'
          id='tiempoAprox'
          {...register("tiempoAprox", {
            required: true,
            valueAsNumber: true,
          })}
          disabled
        />
        <label className='pt-4' htmlFor='ingredientes'>
          ingredientes:
        </label>
        {ingredientesFields.map((ingrediente, index) => {
          return (
            <div
              key={ingrediente.id}
              className='flex flex-row items-end pb-2 w-full gap-3'
            >
              <div className='flex-1'>
                <div className='mr-1'>Ingrediente:</div>
                <input
                  className='flex-1 pl-1 w-full bg-orange-50 focus:bg-orange-100 rounded-lg border border-orange-300 outline-orange-400'
                  {...register(`ingredientes.${index}.nombre`, {
                    required: true,
                  })}
                />
              </div>
              <div>
                <div className='ml-3 mr-1'>Cantidad:</div>
                <input
                  className='pl-1 w-32  bg-orange-50 focus:bg-orange-100 rounded-lg border border-orange-300 outline-orange-400'
                  {...register(`ingredientes.${index}.cantidad`, {
                    required: true,
                  })}
                />
              </div>
              <button
                className='bg-red-600 p-1 text-white rounded-lg font-medium text-center'
                type='button'
                onClick={() => ingredientesRemove(index)}
              >
                Borrar
              </button>
            </div>
          );
        })}
        <button
          className='flex self-center justify-center bg-green-700 text-white  rounded-lg font-medium py-1 px-2'
          type='button'
          onClick={() => {
            ingredientesAppend({ nombre: "", cantidad: 0 });
          }}
        >
          Agregar Ingrediente +
        </button>
        <label htmlFor='pasos'>Pasos:</label>
        {pasosFields.map((paso, index) => {
          return (
            <div
              key={paso.id}
              className='flex flex-row items-end gap-3 pb-2 w-full'
            >
              <div className='flex flex-col flex-1'>
                <div className='flex flex-row items-center'>
                  <div>Paso </div>
                  <input
                    className='pl-1 bg-white'
                    value={index + 1}
                    disabled
                    {...register(`pasos.${index}.numero`)}
                  />
                </div>
                <input
                  className='flex-1 bg-orange-50 focus:bg-orange-100 rounded-lg border border-orange-300 outline-orange-400'
                  {...register(`pasos.${index}.descripcion`, {
                    required: true,
                  })}
                />
              </div>
              <button
                className='bg-red-600 p-1 text-white rounded-lg h-8 font-medium text-center'
                type='button'
                onClick={() => pasosRemove(index)}
              >
                Borrar
              </button>
            </div>
          );
        })}
        <button
          className='flex self-center justify-center bg-green-700 text-white  rounded-lg font-medium py-1 px-5'
          type='button'
          onClick={() => {
            pasosAppend({ numero: pasosFields.length + 1, descripcion: "" });
          }}
        >
          Agregar Paso +
        </button>
        <div>Fotos</div>
        <div className=''>
          {fotosFields.map((foto, index) => {
            return (
              <div className='flex flex-col mb-3' key={foto.id}>
                <div>
                  Foto {index + 1}
                  {index === 0 && "*"}
                </div>
                <input
                  className='pl-1 bg-orange-50 focus:bg-orange-100 rounded-lg border border-orange-300 outline-orange-400'
                  type='url'
                  {...register(`fotos.${index}.url`, { required: false })}
                  placeholder='url de imagen'
                />
              </div>
            );
          })}
        </div>

        <button
          type='submit'
          className='py-2 px-7 bg-blue-600 text-white font-bold w-auto m-auto my-2 rounded-lg'
        >
          GUARDAR CAMBIOS
        </button>
      </form>
    </div>
  );
}
