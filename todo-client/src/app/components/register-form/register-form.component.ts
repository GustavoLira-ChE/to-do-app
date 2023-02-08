import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewUserInfo } from 'src/app/models/new-user-info.model';
import { RegisterFormService } from 'src/app/service/register/register-form.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {

  constructor(
    private fb: FormBuilder,
    private register: RegisterFormService,
    private router: Router
  ){}

  registerForm = this.fb.group({
    firstName:["",[Validators.required]],
    lastName:["",[Validators.required]],
    email:["",[Validators.required,Validators.email]],
    password:["",[Validators.required,Validators.minLength(8)]]
  });

  onSubmitRegister(){
    let info = this.registerForm.getRawValue();
    let newUser;
    if(
      info.firstName != null &&
      info.lastName != null &&
      info.email != null &&
      info.password != null
      ){
        let infoUser: NewUserInfo = {
          "firstName": info.firstName,
          "lastName": info.lastName,
          "email": info.email,
          "password": info.password
        }
        newUser = infoUser;
      }
    if(newUser != undefined){
      this.register.registerNewUser(newUser);
      this.router.navigate(["login"]);
    }
  }
}
