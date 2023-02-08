import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { FooterComponent } from './components/footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { WelcomeViewComponent } from './components/welcome-view/welcome-view.component';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { StoreModule } from '@ngrx/store';
import { userSessionReducer } from './state/reducer/user-session.reducer';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    RegisterFormComponent,
    LoginFormComponent,
    FooterComponent,
    WelcomeViewComponent,
    TodoListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    StoreModule.forRoot({session: userSessionReducer})

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
