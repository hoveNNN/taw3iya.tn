// src/app/shared/error-handler.ts
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

export function handleError<T>(operation = 'operation', result?: T) {
  return (error: HttpErrorResponse): Observable<T> => {
    // Log error to console or send to remote logging infrastructure
    console.error(`${operation} failed: ${error.message}`);

    // Return a safe result so the app can keep running
    return throwError(() => new Error(error.message));
  };
}
