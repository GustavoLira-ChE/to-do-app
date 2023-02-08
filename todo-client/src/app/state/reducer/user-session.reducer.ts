import { createReducer, on } from "@ngrx/store";
import { initialStateSession } from "../state/user-session.state";
import { createUserSession, deleteuserSession } from "../action/user-session.action";
import { UserAndToken } from "src/app/models/user-and-token.model";

const sessionClosed: UserAndToken = {token: null, user: null};

export const userSessionReducer = createReducer(
  initialStateSession,
  on(createUserSession, (state,userSession: UserAndToken) => userSession),
  on(deleteuserSession, (state) => sessionClosed)
);
