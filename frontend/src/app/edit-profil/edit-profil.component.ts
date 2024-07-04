import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

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
    this.route.paramMap.subscribe(result=>{
      let id = result.get('id');
      if (id !="-1") this.initUser(id);
    })
  }
  initUser(id){
    this.userService.getUserById(id).subscribe(user=>{
      this.user=user;
      console.log(this.user);
    })
  }
  onSubmit(){
    this.userService.updateUser(this.user).subscribe(
      user=>{
        this.router.navigate(['/user/'+this.user.id])
      }
    )
  }
  // saveUser() {
  //   if (this.user) {
  //     this.userService.updateUser(this.user);
  //     this.router.navigate(['/user', this.user.id]);
  //   } else {
  //     console.error('User object is undefined.');
  //   }
  // }


}
