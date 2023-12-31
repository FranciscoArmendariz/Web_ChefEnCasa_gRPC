import {
  applyMiddleware,
  combineReducers as CR,
  compose,
  legacy_createStore as createStore,
} from "redux";
import thunk from "redux-thunk";
import {
  fetchMiddleware,
  configureMergeState,
  wrapCombineReducers,
} from "redux-recompose";
import { createWrapper } from "next-redux-wrapper";

import recetas from "./recetas/reducer";
import user from "./user/reducer";

configureMergeState((state, diff) => state.merge(diff));

const combineReducers = wrapCombineReducers(CR);

// Add reducers here
const reducers = combineReducers({
  recetas,
  user,
});

const middlewares = [thunk, fetchMiddleware];
const enhancers = [];

/* ------------- Assemble Middleware ------------- */
enhancers.push(applyMiddleware(...middlewares));

const debug =
  process.env.NODE_ENV === "development" &&
  typeof window !== "undefined" &&
  // eslint-disable-next-line no-underscore-dangle
  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__;

const composeEnhancers = debug || compose;

const rootReducer = (state, action) => reducers(state, action);

const store = createStore(rootReducer, composeEnhancers(...enhancers));

const makeStore = () => store;

export const wrapper = createWrapper(makeStore, { debug });
