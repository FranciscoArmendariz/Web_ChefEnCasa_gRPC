import Header from "@/components/header";
import Borrador from "@/screens/borrador";
import Recetario from "@/screens/recetario";
import { useRouter } from "next/router";

export default function BorradorPage() {
  const router = useRouter();
  const idBorrador = router.query.id;
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Borrador idBorrador={~~idBorrador} />
    </div>
  );
}
