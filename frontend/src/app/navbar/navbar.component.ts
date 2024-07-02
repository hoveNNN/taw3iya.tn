import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../shared/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  users: User[] = [];
  constructor(
    private login: MatDialog,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  openLogIn() {
    this.login.open(LoginComponent);
  }
  isVisible: boolean = false;

  openModal() {
    this.isVisible = true;
  }

  closeModal() {
    this.isVisible = false;
  }
  user: User | undefined;
  idUser: any;

  ngOnInit(): void {
    this.users = this.userService.getUsers();
  }
}
