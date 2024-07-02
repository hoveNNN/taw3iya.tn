import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { SujetComponent } from './sujet/sujet.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { UserProfilComponent } from './user-profil/user-profil.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { EditProfilComponent } from './edit-profil/edit-profil.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { authGuard } from './guards/auth.guard';
import { PostComponent } from './post/post.component';
import { FaqComponent } from './faq/faq.component';

const routes: Routes = [
  {path:'',/*canActivate:[authGuard],*/ component:HomeComponent},
  {path:'user',component:UserComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path: 'sujet',component:SujetComponent},
  {path:'user/:id',component:UserProfilComponent},
  {path:'resetPassword',component:ForgotPasswordComponent},
  {path:'user/:id/edit',component:EditProfilComponent},
  {path:'settings',component:ChangePasswordComponent},
  {path:'post', component:PostComponent},
  {path:'faq',component:FaqComponent},
  {path:'**', component:NotfoundComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
