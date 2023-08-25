import Header from "@/components/header";
import Recipes from "@/screens/recipes";

export default function Home() {
  return (
    <div className='bg-background h-screen'>
      <Header />
      <Recipes />
    </div>
  );
}
