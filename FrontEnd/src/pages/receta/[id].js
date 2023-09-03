import Header from "@/components/header";
import Receta from "@/screens/receta";
import { useRouter } from "next/router";

export default function RecetaPage() {
  const router = useRouter();
  const idReceta = router.query.id;
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Receta idReceta={~~idReceta} />
    </div>
  );
}
