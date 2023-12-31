import Header from "@/components/header";
import Login from "@/screens/login";

export default function LoginPage() {
  return (
    <div className='h-screen bg-background overflow-y-auto'>
      <Header hideButtons />
      <Login />
    </div>
  );
}
