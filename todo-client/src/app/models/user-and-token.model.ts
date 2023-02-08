import { User } from "./user.model"

export interface UserAndToken{
  token: string | null,
  user: User | null
}
