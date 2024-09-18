import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { IAuthenticationRequest, IAuthenticationResponse, IForgotPasswordStepStatus } from '../../models/interfaces';
import { apiEndpoints } from '../../models/apiEndpoints';
import { SessionStorageService } from '../storage/session-storage.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient, private storage: SessionStorageService, private router:Router) { }


  login(authenticationRequest: IAuthenticationRequest): Observable<{ success: boolean, message: string, data?: IAuthenticationResponse }>{
    return this.http.post<IAuthenticationResponse>(apiEndpoints.login, authenticationRequest)
    .pipe(
      map(response => {
        // SI EL LOGIN ES CORRECTO ENTONCES REGRESAMOS LA RESPUESTA AL COMPONENTE
        return { success: true, message: 'Sesión iniciada correctamente', data: response };
      }),
      catchError((error: HttpErrorResponse) => {
        //MENSAJE POR DEFECTO EN CASO DE ERRORES NO CONTROLADOS
        let message = 'En estos momentos no se puede acceder a esta función, inténtelo más tarde.';

        //SI EL ERROR INDICA CREDENCIALES INCORRECTAS ENTONCES MANDAMOS EL MENSAJE CORRESPONDIENTE
        if (error.error.code === 'auth:bad-credentials') {
          message = 'La contraseña ingresada es incorrecta.';
        } else if (error.status === 404 && error.error.code === 'auth:user-not-found') {//EL ERROR INDICA 404 SIGNIFICA QUE EL USUARIO INGRESADO NO EXISTE
          message = 'No hemos podido encontrar un usuario con este correo.';
        } else if (error.status === 403 && error.error.code === 'auth:login-forbidden') {
          message = 'Usted no tiene permitido acceder a la plataforma.'
        }
        //REGRESAMOS EL OBJETO AL COMPONENTE CON LOGIN STATUS Y MENSAJE
        return of({ success: false, message });
      })
    );
  }

  refreshToken(): Observable<any> {
    const refreshToken = this.storage.getRefreshToken();
    return this.http.post(
      apiEndpoints.refreshToken, //RUTA DE LA API
      { 'refresh_token' : refreshToken }, //OBJETO DEL BODY
      {
        headers: new HttpHeaders({
          'authorization': `Bearer ${refreshToken}`, //AUTHORIZATION HEADER CON REFRESH TOKEN  
          'content-Type': 'application/json'
        })
      }
    );
  }

  fpGetVerificationCode(email:string): Observable<any> {
    return this.http.post(
      apiEndpoints.forgotPassword+'/'+ email, //RUTA DE LA API
      {
        headers: new HttpHeaders({
          'content-Type': 'application/json'
        })
      }
    );
  }

  fpChangePasswordWithVerificationCode(email:string, otp: number, newPassword:string, repeatPassword:string): Observable<IForgotPasswordStepStatus> {
    return this.http.post<IForgotPasswordStepStatus>(
      apiEndpoints.forgotPassword+'/'+ email + '/' + otp, //RUTA DE LA API
      { 
        'password' : newPassword,
        'repeat_password' : repeatPassword,
      }, 
      {
        headers: new HttpHeaders({
          'content-Type': 'application/json'
        })
      }
    );
  }

  logout() {
    //logout
    this.storage.removeToken(); //ELIMINA LOS TOKEN DEL STORAGE
    this.storage.removeRefreshToken(); //ELIMINA LOS TOKEN DEL STORAGE
    this.router.navigate(['']); //REDIRIGE AL LOGIN
  }
}
