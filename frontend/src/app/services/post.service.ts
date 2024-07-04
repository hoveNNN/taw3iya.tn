import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Basic_URL='https://localhost:8001/api/post/ajouterPost'
@Injectable({
  providedIn: 'root'
})
export class PostService {
  

  constructor(private http:HttpClient) { }
createNewPost(data:any):Observable<any>{
  return this.http.post(Basic_URL+'api/posts',data)
}
getAllPosts(){
  return this.http.get(Basic_URL+'api/posts')
}
}
