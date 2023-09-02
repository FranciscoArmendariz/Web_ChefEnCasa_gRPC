import { login } from "@/redux/user/actions";
import { useRouter } from "next/router";
import { useDispatch } from "react-redux";
import { useEffect } from "react";

export default function LoginCheck({ children }) {
  const router = useRouter();
  const dispach = useDispatch();

  useEffect(() => {
    const loginData = localStorage.getItem("loginData")?.split("_");
    if (loginData) {
      dispach(login({ usuario: loginData[0], contrasenia: loginData[1] }));
    } else {
      if (router.pathname !== "/login") router.push("/login");
    }
  }, []);

  return children;
}
