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
    // this.idUser = +this.route.snapshot.params['id'];
    // const fetchedUser = this.userService.getUserById(this.idUser);
    // if (fetchedUser) {
    //   this.user = fetchedUser;
    // } else {
    //   console.error(`User with ID ${this.idUser} not found.`);
    // }
  }
  // onSubmit(form:NgForm){
  //   let user:User={
  //     id:null,
  //     fName:form.value.fName,
  //     lName:form.value.lName,
  //     gender:form.value.gender,
  //     email:form.value.email,
  //     role:form.value.role,
  //     image:'images/tlogo.jpg'

  //   }
  //   this.userService.addUser(user).subscribe(
  //     {
  //       next:(user)=>{this.router.navigateByUrl('/user/'+user.id)},
  //       error:(error)=>{console.log('add user Failed')},
  //      complete:()=>console.log('terminer')
  // });
  // this.userService.addUser(user).subscribe(
  //   (contact)=>{ this.router.navigateByUrl("/user")},
  //   (error)=>console.log("error")
  // )
  // }
  saveUser() {
    if (this.user) {
      this.userService.updateUser(this.user);
      this.router.navigate(['/user', this.user.id]);
    } else {
      console.error('User object is undefined.');
    }
  }


}
