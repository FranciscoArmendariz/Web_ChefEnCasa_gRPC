import Header from "@/components/header";
import Favoritos from "@/screens/favoritos";

export default function FavoritosPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header />
      <Favoritos />
    </div>
  );
}
