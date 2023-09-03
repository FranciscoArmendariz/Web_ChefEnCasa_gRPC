import { CATEGORIAS, INGREDIENTES } from "@/constants/camposFiltros";
import { useFieldArray, useForm } from "react-hook-form";

export default function NuevaReceta() {
  const { control, register, handleSubmit } = useForm({
    defaultValues: {
      titulo: "",
      descripcion: "",
      categoria: "",
      tiempoAprox: 0,
      fotos: [{ url: "" }, { url: "" }, { url: "" }],
      ingredientes: [{ nombre: "", cantidad: 0 }],
      pasos: [{ numero: 0, descripcion: "" }],
    },
  });

  const { fotosFields } = useFieldArray({ control, name: "fotos" });
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

  const onSubmit = (data) => {
    console.log(data);
  };
  return (
    <div>
      <form className='flex flex-col p-3' onSubmit={handleSubmit(onSubmit)}>
        <label htmlFor='titulo'>Titulo</label>
        <input
          id='titulo'
          {...register("titulo")}
          placeholder='Nombre de la receta...'
        />
        <label htmlFor='descripcion'>descripción</label>
        <input
          id='descripcion'
          {...register("descripcion")}
          placeholder='Descripcion de la receta...'
        />
        <label htmlFor='categoria'>Categoria de las recetas</label>
        <select id='categoria' {...register("categoria")}>
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
          {...register("tiempoAprox", { valueAsNumber: true })}
          placeholder='Tiempo de cocción...'
        />
        <label htmlFor='ingredientes'>ingredientes</label>
        {ingredientesFields.map((ingrediente, index) => {
          return (
            <div key={ingrediente.id} className='flex flex-row pb-2 w-full'>
              <div>Ingrediente</div>
              <input
                className='flex-1'
                {...register(`ingredientes.${index}.nombre`)}
              />
              <div>Cantidad</div>
              <input
                className=''
                {...register(`ingredientes.${index}.cantidad`)}
              />
              <button
                className='bg-red-600 text-white mx-1 w-6 h-full rounded-lg font-black text-center'
                type='button'
                onClick={() => ingredientesRemove(index)}
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
              <div>Descripcion</div>
              <input
                className='flex-1'
                {...register(`pasos.${index}.descripcion`)}
              />
              <button
                className='bg-red-600 text-white mx-1 w-6 h-full rounded-lg font-black text-center'
                type='button'
                onClick={() => pasosRemove(index)}
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

        <button type='submit'>CREAR</button>
      </form>
    </div>
  );
}
