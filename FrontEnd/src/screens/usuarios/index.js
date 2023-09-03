import { USUARIOS } from "@/constants/usuarios";
import ListaUsuarios from "./components/listaUsuarios";

export default function Usuarios() {
  return (
    <div className='h-full'>
      <div>USUARIOS</div>
      <div className='bg-gray-200 m-auto h-4/6 w-auto mx-10 p-5 rounded-lg relative '>
        <div className='h-full overflow-y-auto flex justify-center'>
          <ListaUsuarios usuarios={USUARIOS} />
        </div>
      </div>
    </div>
  );
}