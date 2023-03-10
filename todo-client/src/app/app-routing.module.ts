import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';

const routes: Routes = [
  {path:"", redirectTo:"/home", pathMatch: 'full'},
  {path:"home", component:HomeComponent},
  {path:"login",component:LoginFormComponent},
  {path:"register",component:RegisterFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
