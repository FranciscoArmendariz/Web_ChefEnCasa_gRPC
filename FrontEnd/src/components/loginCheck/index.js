import { login } from "@/redux/user/actions";
import { useRouter } from "next/router";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";

export default function LoginCheck({ children }) {
  const router = useRouter();
  const dispach = useDispatch();

  useEffect(() => {
    const loginData = localStorage.getItem("loginData")?.split("_");
    if (!loginData) {
      if (router.pathname !== "/login") router.push("/login");
    } else {
      dispach(login({ usuario: loginData[0], contrasenia: loginData[1] }));
    }
  }, []);

  return children;
}
