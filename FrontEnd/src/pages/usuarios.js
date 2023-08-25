import Header from "@/components/header";
import Usuarios from "@/screens/usuarios";

export default function Home() {
  return (
    <div className='h-screen bg-background'>
      <Header />
      <Usuarios />
    </div>
  );
}
