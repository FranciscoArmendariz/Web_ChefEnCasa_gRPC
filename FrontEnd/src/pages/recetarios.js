import Header from "@/components/header";
import Recetarios from "@/screens/recetarios";

export default function RecetariosPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Recetarios />
    </div>
  );
}
