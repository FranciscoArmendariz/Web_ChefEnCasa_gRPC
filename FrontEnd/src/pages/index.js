import Header from "@/components/header";
import Recetas from "@/screens/recetas";

export default function RecetasPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Recetas />
    </div>
  );
}
