import Header from "@/components/header";
import NuevaReceta from "@/screens/nuevaReceta";

export default function RecetasPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <NuevaReceta />
    </div>
  );
}
