import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Subscription, repeat } from 'rxjs';
import { CreateUser } from '../shared/create-user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  successMessage!: string; // Variable to store success messages
  errorMessage!: string; // Variable to store error messages
  AuthUserSub!: Subscription; // Subscription to the authenticated user observable
  registerForm!: FormGroup; // Form group for the registration form
  isLoading: boolean = false; // Variable to track the loading state

  // Inject AuthService, Router, and FormBuilder in the constructor
  constructor(
    private authService: AuthService, // Inject AuthService for authentication
    private router: Router, // Inject Router for navigation
    private formBuilder: FormBuilder // Inject FormBuilder for reactive form creation
  ) { }

  // Lifecycle hook that is called after Angular has initialized all data-bound properties
  ngOnInit() {
    // Subscribe to the AuthenticatedUser$ observable to monitor authentication state
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next: user => {
        // If a user is authenticated, navigate to the home page
        if (user) {
          this.router.navigate(['/']);
        }
      }
    });
    // Initialize the registration form
    this.initForm();
  }

  // Initialize the registration form with validation
  initForm(): void {
    this.registerForm = this.formBuilder.group({
      firstname: ['', Validators.required], // Firstname field with required validator
      lastname: ['', Validators.required], // Lastname field with required validator
      email: ['', [Validators.required, Validators.email]],
      gender:['', [Validators.required]], // Email field with required and email validators
      password: ['', [Validators.required, Validators.minLength(6)]], // Password field with required and minlength validators
      repeatPassword: ['', [Validators.required, Validators.minLength(6)]], // Password field with required and minlength validators
      role: ['', Validators.required] // Role field with required validator
    });
  }

  // Method to handle the sign-up form submission
  onSubmitSignup() {
    // Create a new user object from the form values
    const newUser: CreateUser = {
      firstName: this.registerForm.value.firstname,
      lastName: this.registerForm.value.lastname,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      confirmpassword: this.registerForm.value.repeatPassword,
      gender:this.registerForm.value.gender,
      // role: this.registerForm.value.role
    };
    this.isLoading = true; // Set loading state to true
    // Call the register method from AuthService
    this.authService.register(newUser).subscribe({
      next: response => {
        // On successful registration, show success message, clear error message, reset the form, and stop loading
        this.successMessage = 'Registration successful!';
        this.errorMessage = '';
        this.registerForm.reset();
        this.isLoading = false;
      },
      error: err => {
        // On error, set the error message, clear success message, and stop loading
        this.errorMessage = err.message;
        this.successMessage = '';
        this.isLoading = false;
      }
    });
  }

  // Lifecycle hook that is called when the component is destroyed
  ngOnDestroy() {
    // Unsubscribe from the AuthenticatedUser$ observable to prevent memory leaks
    this.AuthUserSub.unsubscribe();
  }
}
