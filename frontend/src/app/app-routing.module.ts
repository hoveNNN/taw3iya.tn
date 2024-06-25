import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { SujetComponent } from './sujet/sujet.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { UserProfilComponent } from './user-profil/user-profil.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'contact',component:UserComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path: 'sujet',component:SujetComponent},
  {path:'user/:cin',component:UserProfilComponent},
  {path:'**', component:NotfoundComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
