import { useRouter } from "next/router";

export default function Header() {
  const router = useRouter();

  const goToPage = (url) => {
    router.push(url);
  };

  return (
    <div className='w-full h-20  bg-gradient-to-br from-orange-500 to-orange-700 flex flex-row justify-between items-center'>
      <div className='ml-8'>
        <button
          className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2'
          onClick={() => {
            goToPage("/");
          }}
        >
          RECETAS
        </button>
        <button
          className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
          onClick={() => {
            goToPage("/favoritos");
          }}
        >
          FAVORITOS
        </button>
        <button
          className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '
          onClick={() => {
            goToPage("/usuarios");
          }}
        >
          USUARIOS
        </button>
      </div>
      <div className='mr-8'>
        <button className='text-white underline underline-offset-4 decoration-2 font-semibold p-3 m-2 '>
          MI USUARIO
        </button>
      </div>
    </div>
  );
}
