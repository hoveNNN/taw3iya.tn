import { Inject, Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, Subject, tap, throwError } from 'rxjs';
import { CreateUser, LoginDto } from '../shared/create-user';
import { User } from '../shared/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from './storage.service';
import { AuthResponseData } from '../shared/authResponseData';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  AuthenticatedUser$ = new BehaviorSubject<User | null>(null); 
  private isAuth:boolean=false;
  authSubject:Subject<boolean>=new Subject<boolean>()
  constructor(private http: HttpClient,@Inject('BaseURL') private baseURL: any, private storageService: StorageService) { }
  emitAuthSubject(){
    this.authSubject.next(this.isAuthenticated(),)
  }

  isAuthenticated():boolean{
   return this.isAuth;
  }
  signIn(){
    this.isAuth=true;
    this.emitAuthSubject();
  }
  signOut(){
    this.isAuth=false;
    this.emitAuthSubject();
  }
  register(user: CreateUser) {
    return this.http.post<CreateUser>(this.baseURL + 'auth/signup',user).pipe(
      catchError(err => {
        let errorMessage = 'An unknown error occurred!';
        if (err.error.message === 'User already exists') {
          errorMessage = 'This email address is already registered';
        }
        return throwError(() => new Error(errorMessage));
      })
    );
  }

  login(loginDto: LoginDto){
    return this.http.post<LoginDto>(this.baseURL + 'auth/login',loginDto);
    // .pipe(
    //   catchError(err => {
    //     let errorMessage = 'An unknown error occurred!';
    //     if (err.error.message === 'Unauthorized') {
    //       errorMessage = 'unauthorized';
    //     }
    //     return throwError(() => new Error(errorMessage));
    //   })
    // );
  }

  // login(email: string, password: string) {
  //   let httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json', // Set content type to JSON
  //       'Authorization': 'Basic ' + window.btoa(email + ':' + password) // Add basic auth header
  //     }),
  //     withCredentials: true // Include credentials (cookies) in the request
  //   };
    
  //   return this.http.post<AuthResponseData>(this.baseURL + 'signin', null, httpOptions).pipe(
  //     catchError(err => {
  //       let errorMessage = 'An unknown error occurred!';
        
  //      //  if (err.error.message === 'Bad credentials') {
  //       errorMessage = 'The email address or password you entered is invalid';
  //      //  }
  //       return throwError(() => new Error(errorMessage));
  //     }),
  //     tap(user => {
  //       const extractedUser: User = {
  //         email: user.email,
  //         id: user.id,
  //         fName: 'aze',
  //         lName: 'aze',
  //         gender: 'male',
  //         role: 'citoyen',
  //         image: ''
  //       };
  //       this.storageService.saveUser(extractedUser); // Save user to local storage
  //       this.AuthenticatedUser$.next(extractedUser); // Update BehaviorSubject with authenticated user
  //     })
  //   );
  // }

	
}
