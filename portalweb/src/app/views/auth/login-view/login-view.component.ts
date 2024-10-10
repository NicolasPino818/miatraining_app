import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../services/auth/authentication.service';
import { SessionStorageService } from '../../../services/storage/session-storage.service';
import { JwtService } from '../../../services/jwt/jwt.service';
import { isPlatformBrowser, NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { userURLs } from '../../../models/nav';

@Component({
  selector: 'app-login-view',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './login-view.component.html',
  styleUrl: './login-view.component.css'
})
export class LoginViewComponent {

  public submitted: boolean = false; //INDICA SI BOTON DEL FORMULARIO FUE APRETADO
  //LO UTILIZAMOS PARA DESACTIVAR EL BOTON DEL FORMULARIO MIENTRAS SE PROCESA LA REQUEST Y EVITAR SPAM AL BACKEND
  public btnDisabled: boolean = false; 
  public showLoader: boolean = false; //ACTIVA O DESACTIVA EL INDICADOR DE CARGA
  public responseMsg: string = ""; //MENSAJE DE RESPUESTA AL CLIENTE EN CASO DE ERROR
  public showResponseMsg: boolean = false; //ACTIVA O DESACTIVA EL MENSAJE DE ERROR

  //OBJETO DE REACTIVE FORMS QUE MAPPEA EL FORMULARIO HTML
  public loginForm:FormGroup = new FormGroup({
    email: new FormControl('', [
      Validators.email,
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.required
    ]),
  })


  //INJECCION DE DEPENDENCIAS NECESARIAS
  constructor(
    private authService: AuthenticationService, 
    private router: Router, 
    private storage:SessionStorageService, 
    private jwt:JwtService,
    @Inject(PLATFORM_ID) private platformId: Object){

    if (isPlatformBrowser(this.platformId)) this.authService.logout();//AL CARGAR EL LOGIN SIEMPRE HACE LOGOUT

  }

  //SE USA PARA ALTERNAR EL INPUT DE CONTRASEÑA PARA VER O ESCONDER EL CONTENIDO
  tooglePasswordView() : void {
    let input = <HTMLInputElement> document.querySelector("#password"); //TOMA EL INPUT DE CONTRASEÑA POR EL ID
    let toggle = <HTMLInputElement> document.querySelector("#toogleBtn");

    //ALTERNA EL ICONO DEL OJO CERRADO Y ABIERTO Y CAMBIA EL TIPO DE INPUT
    //LOS ICONOS USADOS PARA EL INPUT SON DE GOOGLE FONT ICONS, 
    //IMPORTADOS EN INDEX.HTML COMO ETIQUETA SCRIPT

    toggle.classList.toggle("bx-hide");
    toggle.classList.toggle("bx-show");

    if(toggle.classList.contains("bx-show")){
      input.type = "text";
    }else{
      input.type = "password";
    }
  }

  //EJECUTADA AL MOMENTO DE ENVIAR EL FORMULARIO
  onSubmit(): void{
    this.submitted = true; //ACTIVA EL INTENTO DE SUBMIT
    this.showResponseMsg = false; //POR DEFECTO NO MOSTRAMOS ERROR HASTA QUE LLEGUE LA RESPUESTA DEL SERVIDOR

    if(this.loginForm.valid){ //SOLO INGRESA SI EL FORMULARIO CUMPLE TODAS LAS RESTRICCIONES

      this.btnDisabled = true; //SOLO SI EL FORMULARIO ES VALIDO DESCATIVAMOS EL BOTON DE SUBMIT
      this.showLoader = true; //ACTIVA EL INDICADOR DE CARGA
      this.responseMsg = ""; //ESTABLECE EL MENSAJE POR DEFECTO A VACIO

      let email = this.loginForm.get('email')?.value as string; //OBTIENE EL VALOR DEL USERNAME
      let password = this.loginForm.get('password')?.value as string; //OBTIENE EL VALOR DE LA PASSWORD

      this.authService.login({email,password}) //ENVIA PETICION DE AUTENTICACION A BACKEND
      .subscribe(result => { //LA VARIABLE OBTIENE LOS RESULTADOS DE LA AUTENTICACION

        if (result.success && result.data) { // SI EL LOGIN ES CORRECTO INGRESA AQUI

          let token = this.jwt.getDecodedAccessToken(result.data.access_token);

          this.storage.storeToken(result.data.access_token);
          this.storage.storeRefreshToken(result.data.refresh_token);

          if(token.authorization === 'CLIENT' && token.firstLogin) {
            this.router.navigate(['registro-formulario-inicial']);
            return;
          }else if(token.firstLogin){
            this.authService.setFirstLoginFalse(token.sub).subscribe(()=>{
              this.authService.refreshToken().subscribe((tokens)=>{
                this.storage.storeToken(tokens.access_token);
                this.storage.storeRefreshToken(tokens.refresh_token);
                token = this.jwt.getDecodedAccessToken(tokens.access_token);
                this.handleRedirect(token.authorization);
              });
            });
            return;
          }
          this.handleRedirect(token.authorization);

        } else { //SI EL LOGIN ES INCORRECTO INGRESA AQUI

          this.btnDisabled = false; //AL FINALIZAR EL LOGIN CON ERRORES VOLVEMOS A ACTIVAR EL BOTON
          this.showLoader = false; //AL FINALIZAR EL LOGIN CON ERRORES DESACTIVAMOS EL INDICADOR DE CARGA
          this.responseMsg = result.message; //EL MENSAJE SE MANEJA EN EL AUTHENTICATION SERVICE
          this.showResponseMsg = true; //MOSTRAMOS EL MENSAJE AL USUARIO
        }
      });
    }
  }

  handleRedirect(userRole: string){
    if(userRole === 'ADMIN') this.router.navigate(['/dashboard/'+userURLs.adminBaseUrl]);
    else if(userRole === 'COACH'){}
    else if(userRole === 'MANAGER'){}
    else if(userRole === 'CLIENT') this.router.navigate(['/dashboard/'+userURLs.clientBaseUrl]);
  }

}
