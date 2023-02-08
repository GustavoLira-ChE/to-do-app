import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUserInfo} from 'src/app/models/new-user-info.model';
import { User } from 'src/app/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterFormService {

  private newUserUrl: string = "http://localhost:8089/api/user";

  constructor(
    private http: HttpClient
  ) { }

  registerNewUser(userInfo: NewUserInfo){
    let newUser
    /* let headers= new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:8089/**'
    }); */
    this.http.post<User>(this.newUserUrl,userInfo/*,{headers} */).subscribe( user => {
      newUser = user;
      console.log(newUser);
    });
    return newUser;
  }

}
