import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../shared/user';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  isAuth: boolean = false;
  constructor(
    private login: MatDialog,
    private authService: AuthService
  ) {}

  openLogIn() {
    this.login.open(LoginComponent);
  }

  ngOnInit(): void {
    this.authService.authSubject.subscribe({
      next: (isAuth: boolean) => {
        this.isAuth = isAuth;
      }
    });
    this.authService.emitAuthSubject();
  }
}
