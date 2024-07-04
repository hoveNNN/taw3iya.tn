import { Inject, Injectable } from '@angular/core';
import { User } from '../shared/user'; 
import { user } from '../shared/users'; 
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private users: User[] = user; 
  httpOptions={
    headers:new HttpHeaders({'Content-Type':'application/json'}),
    withCredentials: true
  };

  constructor(private httpClient: HttpClient, @Inject ('BaseURL')private baseURL:any) { }

  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.baseURL+"products");
  }

  getUserById(id: number):Observable<User> {
    return this.httpClient.get<User>(this.baseURL+"products/"+id);
  }

  // deleteUserById(id: number): void {
  //   const index = this.users.findIndex(user => user.id === id); 
  //   if (index !== -1) {
  //     this.users.splice(index, 1);
  //   }
  // }
  deleteUserById(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.baseURL+"products/"+id);
    }
// addUser(user:User):Observable<User>{
//   const httpOptions={
//     headers:new HttpHeaders({'content-type':'application/json'})
//   }
//   return this.httpClient.post<User>(this.baseURL+'products',user,httpOptions)
// }

updateUser(user: User): Observable<User> {
return this.httpClient.put<User>(this.baseURL+'products/'+user.id,user,this.httpOptions)
}
}
  // const httpOptions = {
  //   headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  // };
  // return this.httpClient.put<User>(this.baseURL + 'products/' + user.id, user, httpOptions);