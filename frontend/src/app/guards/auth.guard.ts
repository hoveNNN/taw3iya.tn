import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { map, take } from 'rxjs';
export const authGuard: CanActivateFn = (
  route,
  state
) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  return authService.AuthenticatedUser$.pipe(
    take(1), // take the first one and then unsubscribe automatically
    map(user => {
      // check if route is restricted by role
      //const  roles  = route.data['roles'];
      const { roles } = route.data;
      if(user && user.role && roles.includes(user.role)) {
       return true;
      }
      if(user) {
       return  router.createUrlTree(['/forbidden']);
      }
      return  router.createUrlTree(['/signin']);
    })
  )
};
