import Header from "@/components/header";
import Denuncias from "@/screens/denuncias";

export default function DenunciasPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Denuncias />
    </div>
  );
}
