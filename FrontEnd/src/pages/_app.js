import { Provider } from "react-redux";
import { wrapper } from "../redux/store";
import "@/styles/globals.css";
import LoginCheck from "@/components/loginCheck";

export default function App({ Component, ...rest }) {
  const { store, props } = wrapper.useWrappedStore(rest);
  return (
    <Provider store={store}>
      <LoginCheck>
        <Component {...props.pageProps} />
      </LoginCheck>
    </Provider>
  );
}
