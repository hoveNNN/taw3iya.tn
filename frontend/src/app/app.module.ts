import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {  UserComponent } from './user/user.component';
import { SujetComponent } from './sujet/sujet.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { UserProfilComponent } from './user-profil/user-profil.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatDialogModule} from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { EditProfilComponent } from './edit-profil/edit-profil.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { PostComponent } from './post/post.component';
import { FaqComponent } from './faq/faq.component';
import {HttpClientModule} from '@angular/common/http'
import { BaseURL } from './shared/baseUrl';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { CreatePostComponent } from './create-post/create-post.component';


@NgModule({
  imports: [
    MatChipsModule,
    // Other Angular Material modules
  ],
})
export class YourModule { }




@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    UserComponent,
    SujetComponent,
    LoginComponent,
    SignupComponent,
    NotfoundComponent,
    UserProfilComponent,
    ForgotPasswordComponent,
    EditProfilComponent,
    ChangePasswordComponent,
    PostComponent,
    FaqComponent,
    CreatePostComponent,
    
    
   
  
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
   
    
  ],
  providers: [{
    provide:'BaseURL',useValue:BaseURL
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
