import { useFieldArray, useForm } from "react-hook-form";
import {
  CATEGORIAS,
  INGREDIENTES,
  TIEMPOS_DE_COCCION,
} from "@/constants/camposFiltros";
import { useDispatch } from "react-redux";
import { setFiltro, traer } from "@/redux/recetas/actions";
import { RECETAS } from "@/constants/recetas";
import recetaApi from "@/services/receta";

export default function FormularioFiltros() {
  const { control, register, handleSubmit } = useForm({
    defaultValues: {
      titulo: "",
      categoria: "",
      rangoDeTiempo: { min: 5, max: 30 },
      ingredientes: [""],
    },
  });
  const { fields, append, remove } = useFieldArray({
    control,
    name: "ingredientes",
  });

  const dispach = useDispatch();
  const onSubmit = (data) => {
    dispach(setFiltro(data));
    dispach(
      traer({
        campo: "listaRecetas",
        service: () => {
          recetaApi.getRecetas({
            titulo: data.titulo,
            categoria: data.categoria,
            page: 1,
            size: 12,
          });
        },
      })
    );
  };

  return (
    <form className='flex flex-col p-3' onSubmit={handleSubmit(onSubmit)}>
      <label htmlFor='titulo'>Buscar por titulo</label>
      <input
        id='titulo'
        {...register("titulo")}
        placeholder='Nombre de la receta...'
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
      <label>Tiempo de preparación</label>
      <div className='flex flex-row gap-2'>
        <div className='flex flex-col flex-1'>
          <label htmlFor='rangoDeTiempoMin'>MIN</label>
          <select
            id='rangoDeTiempoMin'
            {...register("rangoDeTiempo.min", { valueAsNumber: true })}
          >
            <option value={null}>-</option>
            {TIEMPOS_DE_COCCION.map((tiempo, index) => {
              return (
                <option key={`min-${index}-${tiempo}`} value={tiempo}>
                  {tiempo}
                </option>
              );
            })}
          </select>
        </div>
        <div className='flex flex-col flex-1'>
          <label htmlFor='rangoDeTiempoMax'>MAX</label>
          <select
            id='rangoDeTiempoMax'
            {...register("rangoDeTiempo.max", { valueAsNumber: true })}
          >
            <option value={null}>-</option>
            {TIEMPOS_DE_COCCION.map((tiempo, index) => {
              return (
                <option key={`max-${index}-${tiempo}`} value={tiempo}>
                  {tiempo}
                </option>
              );
            })}
          </select>
        </div>
      </div>
      <label htmlFor='ingredientes'>Buscar con ingredientes</label>
      {fields.map((field, index) => {
        return (
          <div key={field.id} className='flex flex-row pb-2 w-full'>
            <select className='w-full' {...register(`ingredientes.${index}`)}>
              {INGREDIENTES.map((ingrediente) => {
                return (
                  <option key={`${index}-${ingrediente}`} value={ingrediente}>
                    {ingrediente}
                  </option>
                );
              })}
            </select>
            <button
              className='bg-red-600 text-white mx-1 w-6 h-full rounded-lg font-black text-center'
              type='button'
              onClick={() => remove(index)}
            >
              -
            </button>
          </div>
        );
      })}
      <button
        className='flex self-center justify-center bg-green-700 w-8 h-8 text-white  rounded-lg font-bold '
        type='button'
        onClick={append}
      >
        +
      </button>

      <button type='submit'>Filtrar</button>
    </form>
  );
}
