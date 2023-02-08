import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { catchError, map, throwError } from 'rxjs';
import { AuthCredential } from 'src/app/models/auth-credential.model';
import { UserAndToken } from 'src/app/models/user-and-token.model';
import { User } from 'src/app/models/user.model';
import { createUserSession } from 'src/app/state/action/user-session.action';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  private loginUrl: string = "http://localhost:8089/api/login";
  private userDtaUrl: string = "http://localhost:8089/api/user/user-data/";

  token: string | null = null;
  user: User | null = null;


  constructor(
    private http: HttpClient,
    private store: Store<{session: UserAndToken}>
  ) { }

  login(authCredetial: AuthCredential) {
    return this.http.post(this.loginUrl,authCredetial, {observe: "response"}).pipe(map(res => {
      let token = res.headers.get("Authorization");
      if(token != null){
        this.token = token
        if(this.token != null){
          let userToken: UserAndToken = {
            "token": this.token,
            "user": this.user
          };
          this.store.dispatch(createUserSession(userToken));
        }
      }
    }))
  }

  getUserInfo(authCredetial: AuthCredential, token: string){
    return this.http.get<User>(`${this.userDtaUrl}${authCredetial.email}`,{headers:{"Authorization":token},observe:"body"}).pipe(
      map(res => {
        let user = res;
        if(user.email != null && user){
          this.user = user;
          let userToken: UserAndToken = {
            "token": token,
            "user": this.user
          };
          this.store.dispatch(createUserSession(userToken));
        }
      })/* , catchError((error: HttpErrorResponse) => {
      if (error.status === HttpStatusCode.Conflict) {
        return throwError(() => new Error('Something went wrong in server'));
      }
      if (error.status === HttpStatusCode.Unauthorized) {
        return throwError(() => new Error('Don\'t authorized'));
      }
      return throwError(() => new Error('Ups something went wrong'));
    }) */
    )
  }
}


