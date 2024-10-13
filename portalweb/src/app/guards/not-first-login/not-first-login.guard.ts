import { isPlatformBrowser } from '@angular/common';
import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { JwtService } from '../../services/jwt/jwt.service';

export const notFirstLoginGuard: CanActivateFn = (route, state) => {
  const platformId = inject(PLATFORM_ID); 
  if (!isPlatformBrowser(platformId)) return false; //ESTO ES PORQUE ESTAMOS USANDO SERVER-SIDE-RENDERING

  const router = inject(Router);
  const storage = inject(SessionStorageService);
  const jwtService = inject(JwtService); //SERVICIO DE JWT 
  const token = jwtService.getDecodedAccessToken(storage.getToken() as string);
  if(!token.firstLogin){
    return true;
  }
  router.navigate(['/auth/login']);
  return false;
};
