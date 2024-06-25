import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-profil',
  templateUrl: './user-profil.component.html',
  styleUrls: ['./user-profil.component.css']
})
export class UserProfilComponent implements OnInit {
  user:User | undefined;
 constructor(private userService:UserService){
 }
 ngOnInit(): void {
     this.user=this.userService.getUserById(123)
 }
}
