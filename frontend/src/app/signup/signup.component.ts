import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../shared/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,private userService:UserService) {
   
    this.userForm = this.formBuilder.group({
      fName: ['', Validators.required],
      lName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      role: ['', Validators.required],
      image: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  
     this.initForm();
  }


  initForm(): void {
    this.userForm = this.formBuilder.group({
      fName: ['', Validators.required],
      lName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      role: ['', Validators.required],
      image: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.userForm) { 
      let user: User = {
        id: -1,
        fName: this.userForm.get('fName')?.value,
        lName: this.userForm.get('lName')?.value,
        email: this.userForm.get('email')?.value,
        gender: this.userForm.get('gender')?.value,
        role: this.userForm.get('role')?.value,
        image: this.userForm.get('image')?.value,
      };
      console.log(user);
    } else {
      console.error("userForm is null!"); 
    } 
    
  }


  onLogIn(): void {
    this.router.navigate(['/login']);
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
}
