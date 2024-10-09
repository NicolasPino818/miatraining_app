import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../../../services/jwt/jwt.service';
import { SessionStorageService } from '../../../../services/storage/session-storage.service';
import { isPlatformBrowser, NgIf } from '@angular/common';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthenticationService } from '../../../../services/auth/authentication.service';
import { catchError, EMPTY } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-reset-password-view',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './reset-password-view.component.html',
  styleUrl: './reset-password-view.component.css'
})
export class ResetPasswordViewComponent {

  currentUserEmail!: string | null;
  private success:boolean = false;
  private message!: string;
  private showLoader:boolean = false;
  private submitFinished: boolean = false;
  resetPasswordForm: FormGroup = new FormGroup({
    currentPassword: new FormControl('', [Validators.required]),
    newPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]),
    repeatPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]),
  }, { validators: passwordMatchValidator });

  constructor(private jwtService: JwtService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private storage: SessionStorageService,
    private authService:AuthenticationService){
      if(isPlatformBrowser(this.platformId)){
        let token = this.storage.getToken();
        this.currentUserEmail = this.jwtService.getDecodedAccessToken(token as string).sub;
      }
  }

  public getEmail():string|null{ return this.currentUserEmail; }
  public getShowLoader():boolean{ return this.showLoader; }
  public isSuccess():boolean{ return this.success; }
  public isSubmitFinished():boolean{ return this.submitFinished; }
  public getFinalMessage(): string{return this.message;}
  private alternateSentStatus(){
    this.showLoader = !this.showLoader;
  }

  onSubmit(){
    this.alternateSentStatus();

    if(this.resetPasswordForm.invalid) {
      this.alternateSentStatus();
      return;
    }

    let currentPassword = this.resetPasswordForm.get('currentPassword')?.value as string;
    let newPassword = this.resetPasswordForm.get('newPassword')?.value as string;
    let repeatPassword = this.resetPasswordForm.get('repeatPassword')?.value as string;

    this.authService.fpResetPassword(this.currentUserEmail as string,{
      currentPassword,
      newPassword,
      repeatPassword
    })
    .pipe(
      catchError((error: HttpErrorResponse) => {
        if(error.status === 404) { 
          this.message = 'El correo electrónico ingresado no existe.';
        }else if(error.status === 417 && error.error.code === 'auth:reset-password-failure'){
          this.message = 'Las contraseñas no coinciden, revisa tu contraseña actual y asegurate de llenar las nuevas correctamente.';
        }else{
          this.message = 'La solicitud ha fallado de forma inesperada.';
        }
        this.success = false;
        this.submitFinished = true;
        this.alternateSentStatus();
        return EMPTY;
      })
    )
    .subscribe((res)=>{
      if(res.success) {
        this.success = true;
        this.message = 'La contraseña ha sido cambiada correctamente.';
        this.submitFinished = true;
      }
      this.alternateSentStatus();
    });

  }

}

function passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const newPassword = control.get('newPassword');
  const repeatPassword = control.get('repeatPassword');

  if (newPassword?.value !== repeatPassword?.value) {
    return { 'mismatch': true };
  }
  return null;
}