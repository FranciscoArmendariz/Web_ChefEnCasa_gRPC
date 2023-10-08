import { CATEGORIAS } from "@/constants/camposFiltros";
import { useFieldArray, useForm } from "react-hook-form";

export default function FormularioReceta({ recetas }) {
  const { control, register, handleSubmit } = useForm({
    defaultValues: editar
      ? {
          ...recetaEditar,
          fotos: [
            { url: recetaEditar.fotos[0]?.url },
            { url: recetaEditar.fotos[1]?.url },
            { url: recetaEditar.fotos[2]?.url },
            { url: recetaEditar.fotos[3]?.url },
            { url: recetaEditar.fotos[4]?.url },
          ],
        }
      : {
          titulo: "",
          descripcion: "",
          categoria: "",
          tiempoAprox: null,
          fotos: [
            { url: "" },
            { url: "" },
            { url: "" },
            { url: "" },
            { url: "" },
          ],
          ingredientes: [{ nombre: "", cantidad: 0 }],
          pasos: [{ numero: 0, descripcion: "" }],
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
        <label htmlFor='titulo'>Titulo</label>
        <input
          id='titulo'
          {...register("titulo", { required: true })}
          placeholder='Nombre de la receta...'
        />
        <label htmlFor='descripcion'>descripción</label>
        <input
          id='descripcion'
          {...register("descripcion", { required: true })}
          placeholder='Descripcion de la receta...'
        />
        <label htmlFor='categoria'>Categoria de las recetas</label>
        <select id='categoria' {...register("categoria", { required: true })}>
          <option value={""}>todas</option>
          {CATEGORIAS.map((categoria) => {
            return (
              <option key={categoria} value={categoria}>
                {categoria}
              </option>
            );
          })}
        </select>
        <label htmlFor='tiempoAprox'>Tiempo de preparación</label>
        <input
          id='tiempoAprox'
          {...register("tiempoAprox", { required: true, valueAsNumber: true })}
          placeholder='Tiempo de cocción...'
        />
        <label htmlFor='ingredientes'>ingredientes</label>
        {ingredientesFields.map((ingrediente, index) => {
          return (
            <div key={ingrediente.id} className='flex flex-row pb-2 w-full'>
              <div className='mr-1'>Ingrediente:</div>
              <input
                className='flex-1 pl-1'
                {...register(`ingredientes.${index}.nombre`, {
                  required: true,
                })}
              />
              <div className='ml-3 mr-1'>Cantidad:</div>
              <input
                className='pl-1 w-32'
                {...register(`ingredientes.${index}.cantidad`, {
                  required: true,
                })}
              />
              <button
                className='bg-red-600 text-white mx-1 w-6 h-full rounded-lg font-black text-center'
                type='button'
                onClick={() =>
                  ingredientesFields.length > 1 && ingredientesRemove(index)
                }
              >
                -
              </button>
            </div>
          );
        })}
        <button
          className='flex self-center justify-center bg-green-700 w-8 h-8 text-white  rounded-lg font-bold '
          type='button'
          onClick={() => {
            ingredientesAppend({ nombre: "", cantidad: 0 });
          }}
        >
          +
        </button>
        <label htmlFor='pasos'>Pasos</label>
        {pasosFields.map((paso, index) => {
          return (
            <div key={paso.id} className='flex flex-row pb-2 w-full'>
              <div>Paso</div>
              <input
                className='pl-1 w-7'
                value={index + 1}
                disabled
                {...register(`pasos.${index}.numero`)}
              />
              <input
                className='flex-1'
                {...register(`pasos.${index}.descripcion`, { required: true })}
              />
              <button
                className='bg-red-600 text-white mx-1 w-6 h-full rounded-lg font-black text-center'
                type='button'
                onClick={() => pasosFields.length > 1 && pasosRemove(index)}
              >
                -
              </button>
            </div>
          );
        })}
        <button
          className='flex self-center justify-center bg-green-700 w-8 h-8 text-white  rounded-lg font-bold '
          type='button'
          onClick={() => {
            pasosAppend({ numero: pasosFields.length + 1, descripcion: "" });
          }}
        >
          +
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
                  type='url'
                  {...register(`fotos.${index}.url`, { required: index === 0 })}
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
          {editar ? "GUARDAR CAMBIOS" : "CREAR"}
        </button>
      </form>
    </div>
  );
}
