import { Injectable } from '@angular/core';
import { User } from '../shared/user';
import { USERS } from '../shared/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  users:User[]=USERS
  constructor() { }
  getUsers():User[]{
    return this.users;
  }
  getUserById(id:number):User |undefined{
    return this.users.find(user=>{user.id==id});
  }
  deleteUserById(id:number):void{
    let index=this.users.findIndex(user=>user.id==id);
    if(index!=-1){
      this.users.splice(index,1)
    }
  }
}
