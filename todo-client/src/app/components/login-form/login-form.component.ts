import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable, Subscription } from 'rxjs';
import { AuthCredential } from 'src/app/models/auth-credential.model';
import { UserAndToken } from 'src/app/models/user-and-token.model';
import { LoginServiceService } from 'src/app/service/login/login-service.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnDestroy {

  tokenUser$: Observable<UserAndToken>;

  subsLogin: Subscription = Subscription.EMPTY;
  subsTokenUser: Subscription = Subscription.EMPTY;

  constructor(
    private fb: FormBuilder,
    private login: LoginServiceService,
    private router: Router,
    private store: Store<{session: UserAndToken}>
  ){
    this.tokenUser$ = this.store.select('session');
  }

  loginForm = this.fb.group({
    email: ["",[Validators.required,Validators.email]],
    password: ["",[Validators.required]]
  })

  onSubmitLogin(){
    let authC = this.loginForm.getRawValue();
    if(authC.email != null && authC.password != null){
      let credential: AuthCredential = {"email": authC.email, "password": authC.password};
      this.subsLogin = this.login.login(credential).subscribe();
      this.subsTokenUser = this.tokenUser$.subscribe(data=> {
        if(data.token != null){
          this.login.getUserInfo(credential, data.token).subscribe();
          this.router.navigate(["home"]);
        }
      });
    }
  }

  ngOnDestroy(): void {
    this.subsLogin.unsubscribe();
    this.subsTokenUser.unsubscribe();
  }
}
