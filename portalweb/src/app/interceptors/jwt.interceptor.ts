import { HttpErrorResponse, HttpHandlerFn, HttpHeaders, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { SessionStorageService } from '../services/storage/session-storage.service';
import { AuthenticationService } from '../services/auth/authentication.service';
import { inject } from '@angular/core';
import { catchError, EMPTY, Observable, switchMap, throwError } from 'rxjs';
import { apiEndpoints } from '../models/apiEndpoints';

export const jwtInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn) => {
  const storage = inject(SessionStorageService); // Inyectamos el servicio de almacenamiento de sesión
  const authService = inject(AuthenticationService); // Inyectamos el servicio de autenticación

  // Si la URL es pública, dejamos pasar la solicitud sin hacer nada
  if (isPublicUrl(req)) {
    return next(req);
  }

  const token = storage.getToken(); // Obtenemos el token desde el almacenamiento
  if (token) {
    const headersReq = AddTokenHeader(req, token); // Añadimos el token a los headers de la solicitud
    return next(headersReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 403 && error.error.code === 'auth:expired-token') {
          // Si el token está expirado, manejamos el refresco
          return handleTokenRefresh(headersReq, next, authService, storage);
        } else if (error.status === 401 && error.error.code === 'auth:forged-token') {
          // Si el token está manipulado, cerramos sesión
          authService.logout();
        }
        // Aquí puedes agregar cualquier manejo adicional para errores de login

        // Propagar el error para que otros catchError lo manejen
        return throwError(() => error);
      })
    );
  }

  authService.logout(); // Si no hay token, cerramos sesión automáticamente
  return EMPTY; // No enviamos la solicitud si no hay token
};

// Función que verifica si la URL es pública
const isPublicUrl = (req: HttpRequest<any>): boolean => {
  const publicUrls = [
    apiEndpoints.login,
    apiEndpoints.refreshToken,
    apiEndpoints.forgotPassword+'/verify',
  ]
  return publicUrls.some(url => req.url.includes(url));
};

// Función que añade el token a los headers
const AddTokenHeader = (req: HttpRequest<any>, token: string): HttpRequest<any> => {
  return req.clone({
    headers: new HttpHeaders({
      'Authorization': `Bearer ${token}`
    })
  });
};

// Función que maneja la renovación del token
const handleTokenRefresh = (
  req: HttpRequest<any>,
  next: HttpHandlerFn,
  authService: AuthenticationService,
  storage: SessionStorageService
): Observable<any> => {
  return authService.refreshToken().pipe(
    switchMap((newAccessToken: any) => {
      storage.storeToken(newAccessToken.access_token);
      storage.storeRefreshToken(newAccessToken.refresh_token);
      const headersReq = AddTokenHeader(req, newAccessToken.access_token);
      return next(headersReq);
    }),
    catchError((error: HttpErrorResponse) => {

      if (error.status === 403 && error.error.code === 'auth:expired-token') {
        alert('Sesión caducada por inactividad');
        authService.logout();
        return EMPTY;
      }

      return throwError(() => error); // Propagamos el error
    })
  );
};
