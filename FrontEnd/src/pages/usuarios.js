import Header from "@/components/header";
import Usuarios from "@/screens/usuarios";

export default function UsuariosPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Usuarios />
    </div>
  );
}
