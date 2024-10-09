import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { JwtService } from '../../services/jwt/jwt.service';
import { isPlatformBrowser } from '@angular/common';
import { rolesEnum } from '../../models/rolesEnum';

export const isClientGuard: CanActivateFn = (route, state) => {
  const platformId = inject(PLATFORM_ID); 
  if (!isPlatformBrowser(platformId)) return false; //ESTO ES PORQUE ESTAMOS USANDO SERVER-SIDE-RENDERING

  const storage = inject(SessionStorageService); //SERVICIO DE STORAGE
  const jwtService = inject(JwtService); //SERVICIO DE JWT
  const router = inject(Router);

  //OBTENEMOS EL TOKEN DEL STORAGE Y LO DECODIFICAMOS
  const decodedToken = jwtService.getDecodedAccessToken(storage.getToken() as string);

  //RETORNAMOS EL RESULTADO DE LA COMPARACION ENTRE EL ROL DEL USUARIO Y EL MAPEO DEL ESTUDIANTE EN EL ENUM
  let result = decodedToken.authorization === rolesEnum[rolesEnum.CLIENT]
  if(!result) {
    //location.back();//REDIRIGE A LA PAGINA ANTERIOR O EN SU DEFECTO AL ROOT
    router.navigate(['auth','login']);
    return false;
  };
  return true;
};
