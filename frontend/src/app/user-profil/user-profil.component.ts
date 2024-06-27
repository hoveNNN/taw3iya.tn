import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-profil',
  templateUrl: './user-profil.component.html',
  styleUrls: ['./user-profil.component.css']
})
export class UserProfilComponent implements OnInit {
  user: User | undefined;
  idUser: any; 

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    // snapshot
    this.idUser = +this.route.snapshot.params['id']; 
    // this.route.paramMap.subscribe(
    //   res=>{this.idUser=res.get('id')}
    // )
        this.user = this.userService.getUserById(this.idUser);
    console.log(this.user);
  }

  goToUsers() {
    this.router.navigateByUrl("/user");
  }
}