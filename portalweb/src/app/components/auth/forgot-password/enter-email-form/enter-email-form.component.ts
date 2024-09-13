import { NgIf } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/auth/authentication.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY } from 'rxjs';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-enter-email-form',
  standalone: true,
  imports: [NgIf,ReactiveFormsModule,RouterLink],
  templateUrl: './enter-email-form.component.html',
  styleUrl: './enter-email-form.component.css'
})
export class EnterEmailFormComponent {

  @Output() 
  private emailCodeSentStatus: 
    EventEmitter<{email:string,success:boolean}> = new EventEmitter<{email:string,success:boolean}>();

  private success:boolean = false;
  private email:string = '';
  private message!: string | null;
  private submitted: boolean = false;
  private showLoader:boolean = false;
  private btnDisabled: boolean = false;

  public emailForm: FormGroup = new FormGroup({
    email: new FormControl('',[
      Validators.required,
      Validators.email,
      Validators.max(254),
      Validators.min(6)
    ])
  });

  constructor(private authService: AuthenticationService){}

  public getEmail():string{ return this.email; }
  public getMessage():string | null { return this.message; }
  public isSuccess():boolean{ return this.success; }
  public isSubmitted():boolean{ return this.submitted; }
  public isBtnDisabled():boolean{ return this.btnDisabled; }
  public getShowLoader():boolean{ return this.showLoader; }

  public sendSuccessStatus() {
    this.emailCodeSentStatus.emit({
      email: this.getEmail(),
      success: this.isSuccess()
    });
  }

  private alternateSentStatus(){
    this.btnDisabled = !this.btnDisabled;
    this.showLoader = !this.showLoader;
  }

  public onSubmit(){
    this.submitted = true;
    this.message = null;

    if(this.emailForm.valid) {
      this.alternateSentStatus();

      this.email = this.emailForm.get('email')?.value as string;
      this.authService.fpGetVerificationCode(this.email)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.success = false;
          this.message = 'No hemos podido procesar la solicitud, inténtelo más tarde.';
          if(error.status === 404 && error.error.code === 'auth:user-not-found') { 
            this.message = 'Este correo no está registrado en el sistema.';
          }else if(error.status === 403 && error.error.code === 'auth:reset-password-forbidden'){
            this.message = 'Usted no tiene permitido reestablecer contraseña.'
          }
          this.alternateSentStatus();
          return EMPTY;
        })
      )
      .subscribe((res)=>{
        if(res.success) {
          this.success = true;
          this.sendSuccessStatus();
        }
      })
    }
  }
}
