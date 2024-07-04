import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-profil',
  templateUrl: './edit-profil.component.html',
  styleUrls: ['./edit-profil.component.css']
})
export class EditProfilComponent implements OnInit {
  user: User | undefined;
  idUser: number | undefined;

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {
  
  }

  ngOnInit(): void {
    this.idUser = +this.route.snapshot.params['id'];
    const fetchedUser = this.userService.getUserById(this.idUser);
    if (fetchedUser) {
      this.user = fetchedUser;
    } else {
      console.error(`User with ID ${this.idUser} not found.`);
    }
  }

  saveUser() {
    if (this.user) {
      this.userService.updateUser(this.user);
      this.router.navigate(['/user', this.user.id]);
    } else {
      console.error('User object is undefined.');
    }
  }


}
