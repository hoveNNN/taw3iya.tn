import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isAuth:boolean=false;

  constructor(public dialogRef: MatDialogRef<LoginComponent>, private router: Router,private authService:AuthService) {}
  ngOnInit(): void {
    this.isAuth=this.authService.isAuthenticated();
  }

  closeModal() {
    this.dialogRef.close();
  }
  navigateToSignUp() {
    this.closeModal();
    this.router.navigate(['/signup']); 
  }
  navigateToForgotPassword() {
    this.closeModal();
    this.router.navigate(['/resetPassword']); 
  }
  
  
  }
  
  

