import FeatherIcon from "feather-icons-react/build/FeatherIcon";

export default function ListaUsuarios({ usuarios }) {
  return (
    <div className='grid grid-cols-3  max-h-full gap-1'>
      {usuarios.map((usuario) => {
        return (
          <button
            key={`${usuario.id}-${usuario.nombre}`}
            className='flex flex-row p-1 bg-gray-100 shadow-md w-80 rounded-md'
          >
            <div className='flex flex-col w-14 h-14 m-1 bg-white items-center rounded-full'>
              <FeatherIcon
                size={35}
                icon={"user-plus"}
                className='stroke-1 relative left-1 top-1'
              />
              <div className=' text-sm font-thin'>seguir</div>
            </div>
            <div className='text-left pl-2'>
              <div>{usuario.nombre}</div>
              <div>@{usuario.usuario}</div>
              <div>{usuario.email}</div>
            </div>
          </button>
        );
      })}
    </div>
  );
}
