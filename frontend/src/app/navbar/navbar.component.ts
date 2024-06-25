import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private login:MatDialog){
  }
  openLogIn(){
   this.login.open(LoginComponent)
  }
  isVisible: boolean = false; // Flag to control modal visibility

  openModal() {
    this.isVisible = true;
  }

  closeModal() {
    this.isVisible = false;
  }
 
}
