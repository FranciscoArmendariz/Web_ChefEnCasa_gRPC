import Header from "@/components/header";
import EditarReceta from "@/screens/editarReceta";
import { useRouter } from "next/router";

export default function EditarRecetaPage() {
  const router = useRouter();
  const idReceta = router.query.id;
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <EditarReceta idReceta={~~idReceta} />
    </div>
  );
}
