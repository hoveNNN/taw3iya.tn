import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(public dialogRef: MatDialogRef<LoginComponent>, private router: Router) {}

  closeModal() {
    this.dialogRef.close();
  }
  navigateToSignUp() {
    this.closeModal();
    this.router.navigate(['/signup']); // Replace with your actual sign-up route
  }
  
  }
  
  

