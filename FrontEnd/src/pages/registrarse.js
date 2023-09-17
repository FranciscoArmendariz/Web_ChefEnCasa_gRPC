import Header from "@/components/header";
import Registrarse from "@/screens/Registrarse";

export default function registrarsePage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header hideButtons />
      <Registrarse />
    </div>
  );
}
