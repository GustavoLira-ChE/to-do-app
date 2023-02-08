import { createAction } from "@ngrx/store";
import { UserAndToken } from "src/app/models/user-and-token.model";

export const createUserSession = createAction("[UserAndToken] create user session", (us: UserAndToken) => us);
export const deleteuserSession = createAction("[UserAndToken] delete user session");
