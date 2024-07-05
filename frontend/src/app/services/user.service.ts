import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../shared/user';
import { handleError } from '../shared/error-handler'; // Centralized error handler

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl: string;
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private httpClient: HttpClient,
    @Inject('BaseURL') private baseURL: string // Specify type explicitly
  ) {
    this.userUrl = `${this.baseURL}users`; // Use template literals for clarity
  }

  // Fetch all users
  getUsers(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userUrl)
      .pipe(
        catchError(handleError<User[]>('getUsers', [])) // Centralized error handling
      );
  }

  // Fetch user by ID
  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.userUrl}/${id}`)
      .pipe(
        catchError(handleError<User>(`getUserById id=${id}`)) // Centralized error handling
      );
  }

  // Delete user by ID
  deleteUserById(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.userUrl}/${id}`)
      .pipe(
        catchError(handleError<void>(`deleteUserById id=${id}`)) // Centralized error handling
      );
  }

  // Update user details
  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.userUrl}/${user.id}`, user, this.httpOptions)
      .pipe(
        catchError(handleError<User>('updateUser')) // Centralized error handling
      );
  }
}
