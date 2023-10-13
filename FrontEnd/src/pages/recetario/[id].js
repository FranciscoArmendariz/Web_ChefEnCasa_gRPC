import Header from "@/components/header";
import Recetario from "@/screens/recetario";
import { useRouter } from "next/router";

export default function RecetarioPage() {
  const router = useRouter();
  const idRecetario = router.query.id;
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Recetario idRecetario={~~idRecetario} />
    </div>
  );
}
