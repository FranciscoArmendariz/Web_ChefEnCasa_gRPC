import { useForm } from "react-hook-form";
import { CATEGORIAS, TIEMPOS_DE_COCCION } from "@/constants/camposFiltros";
import { useDispatch, useSelector } from "react-redux";
import { setFiltro } from "@/redux/recetas/actions";

export default function FormularioFiltros() {
  const dispach = useDispatch();
  const { register, handleSubmit } = useForm({
    defaultValues: {
      titulo: "",
      categoria: "",
      rangoDeTiempo: { min: 5, max: 30 },
      ingrediente: "",
    },
  });
  const ingredientes = useSelector((state) => state.recetas.ingredientes);
  const onSubmit = (data) => {
    dispach(
      setFiltro({
        titulo: data.titulo,
        categoria: data.categoria,
        minTiempoAprox: data.rangoDeTiempo.min,
        maxTiempoAprox: data.rangoDeTiempo.max,
        page: 1,
        size: 12,
        orderBy: "asc",
        sortBy: "id",
        nombreIngrediente: data.ingrediente,
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
      <label htmlFor='ingredientes'>Buscar con ingrediente</label>
      <select {...register("ingrediente")}>
        <option value=''>-</option>
        {ingredientes?.map((ingrediente) => {
          return (
            <option key={ingrediente.nombre} value={ingrediente.nombre}>
              {ingrediente.nombre}
            </option>
          );
        })}
      </select>
      <button
        className='py-2 px-7 bg-blue-600 text-white font-bold w-min m-auto my-2 rounded-lg'
        type='submit'
      >
        Filtrar
      </button>
    </form>
  );
}
