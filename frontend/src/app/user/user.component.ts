import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../shared/user';

@Component({
  selector: 'app-contact',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  users: User[] = [];

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit(): void {
    // this.users = this.userService.getUsers();
    this.userService.getUsers().subscribe(
      {
        next:(users:User[])=>{this.users=users}
        
      }
    )
    console.log('users'+this.users);
  }

  onDelete(id: number) {
    this.userService.deleteUserById(id).subscribe(
      {
        next:(res:any)=>{
          let index=this.users.findIndex(user=> user.id===id);
          if (index !=-1){
            this.users.splice(index,1);
          }
        }
      }
    )
  }
}
