import Header from "@/components/header";
import UsuarioListaRecetas from "@/screens/usuarioListaRecetas";
import { useRouter } from "next/router";

export default function UsuarioRecetasPage() {
  const router = useRouter();
  const usuario = router.query.usuario?.split("-");

  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <UsuarioListaRecetas
        idUsuario={usuario && ~~usuario[0]}
        nombre={usuario && usuario[1]}
        misRecetas={usuario && usuario[2] === "true"}
      />
    </div>
  );
}
