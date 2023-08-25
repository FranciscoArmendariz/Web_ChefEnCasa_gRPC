import Header from "@/components/header";
import Favoritos from "@/screens/favoritos";

export default function Home() {
  return (
    <div className='h-screen bg-background'>
      <Header />
      <Favoritos />
    </div>
  );
}
