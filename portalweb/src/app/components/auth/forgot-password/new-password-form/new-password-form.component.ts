import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../../services/auth/authentication.service';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError, EMPTY } from 'rxjs';

@Component({
  selector: 'app-new-password-form',
  standalone: true,
  imports: [ReactiveFormsModule,NgIf],
  templateUrl: './new-password-form.component.html',
  styleUrl: './new-password-form.component.css'
})
export class NewPasswordFormComponent {

  @Output() 
  private otpPasswordVerificationStatus: 
    EventEmitter<{success:boolean, statusCode: number, message: string}> = 
    new EventEmitter<{success:boolean, statusCode: number, message: string}>();
  
  @Input()
  public email!: string;
  private success:boolean = false;
  private message!: string | null;
  private statusCode!: number;
  private showLoader:boolean = false;

  public otpForm: FormGroup = new FormGroup({
    otp1: new FormControl({ value: null, disabled: false }, [Validators.required, Validators.pattern('[0-9]')]),
    otp2: new FormControl({ value: null, disabled: true }, [Validators.required, Validators.pattern('[0-9]')]),
    otp3: new FormControl({ value: null, disabled: true }, [Validators.required, Validators.pattern('[0-9]')]),
    otp4: new FormControl({ value: null, disabled: true }, [Validators.required, Validators.pattern('[0-9]')]),
    otp5: new FormControl({ value: null, disabled: true }, [Validators.required, Validators.pattern('[0-9]')]),
    otp6: new FormControl({ value: null, disabled: true }, [Validators.required, Validators.pattern('[0-9]')]),

    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]),
    repeatPassword: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]),
  }, { validators: passwordMatchValidator });

  constructor(private authService:AuthenticationService){}

  public getEmail():string{ return this.email; }
  public getShowLoader():boolean{ return this.showLoader; }
  public isSuccess():boolean{ return this.success; }
  private alternateSentStatus(){
    this.showLoader = !this.showLoader;
  }

  public sendVerificationStatus() {
    this.otpPasswordVerificationStatus.emit({
      success: this.isSuccess(),
      message: this.message as string,
      statusCode: this.statusCode
    });
  }

  onSubmit(): void {
    if (this.otpForm.valid) {
      let otp:string = '';
      for (let i = 1; i <= 6; i++) {
        otp += this.otpForm.get(`otp${i}`)?.value || '';
      }
      
      this.alternateSentStatus();
      const password = this.otpForm.value.password;
      const repeatPassword = this.otpForm.value.repeatPassword;

      this.authService.fpChangePasswordWithVerificationCode(this.email, Number.parseInt(otp), password, repeatPassword)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if(error.status === 404) { 
            this.message = 'El correo electrónico ingresado no existe.';
          }else if(error.status === 417){
            this.message = 'El código de verificación está expirado.';
          }else if(error.status === 401){
            this.message = 'El código de verificación no es correcto.';
          }else{
            this.message = 'La solicitud ha fallado de forma inesperada.';
          }
          this.statusCode = error.status;
          this.success = false;
          this.sendVerificationStatus();
          return EMPTY;
        })
      )
      .subscribe((res)=>{
        if(res.success) {
          this.success = true;
          this.message = 'La contraseña ha sido cambiada correctamente.';
          this.sendVerificationStatus();
        }
      });
    }
  }
  
  onKeyUp(event: KeyboardEvent, nextInput: any, previousInput: any): void {
    const input = event.target as HTMLInputElement;
  
    if (event.key === 'ArrowLeft' || event.key === 'ArrowRight' || event.key === 'ArrowUp' || event.key === 'ArrowDown') {
      event.preventDefault(); // Prevent arrow key navigation
    }
  
    if (event.key === 'Backspace') {
      // Handle backspace: if the current input is empty, move focus to the previous input
      if (input.value.length === 0 && previousInput) {
        previousInput.focus();
      }
    } else if (input.value.length === 1 && nextInput) {
      // Move to the next input if a single digit is entered
      nextInput.focus();
    }
  
    this.updateInputStates();
  }
  

  onPaste(event: ClipboardEvent): void {
    event.preventDefault();
    const pasteData = event.clipboardData?.getData('text') || '';
    
    if (/^\d{6}$/.test(pasteData)) {
      const valueArray = pasteData.split('');
      valueArray.forEach((digit, index) => {
        const controlName = `otp${index + 1}`;
        const control = this.otpForm.get(controlName);
        control?.setValue(digit);
        control?.enable({ emitEvent: false }); // Enable the control after setting the value
      });
  
      // Focus the last input
      const lastInput = document.querySelector(`input[formControlName=otp6]`) as HTMLInputElement | null;
      lastInput?.focus();
    }
  
    this.updateInputStates();
  }

  onInput(event: Event, nextInput: any): void {
    const input = event.target as HTMLInputElement;
  
    // Ensure only one digit is kept
    if (input.value.length > 1) {
      input.value = input.value.slice(-1);
    }
  
    const controlName = input.getAttribute('formControlName');
    if (controlName !== null) {
      this.otpForm.get(controlName)?.setValue(input.value);
    }
  
    if (input.value.length === 1 && nextInput) {
      nextInput.focus(); // Move focus to the next input
    }
  
    this.updateInputStates(); // Re-evaluate which inputs should be enabled
  }
  
  updateInputStates(): void {
    let previousInputFilled = true;
  
    for (let i = 1; i <= 6; i++) {
      const currentControl = this.otpForm.get(`otp${i}`);
  
      if (currentControl) {
        if (previousInputFilled) {
          currentControl.enable({ emitEvent: false });
        } else {
          currentControl.disable({ emitEvent: false });
        }
  
        // Check if the current input has a value to decide the state of the next input
        previousInputFilled = currentControl.value !== '';
      }
    }
  }
}

function passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const password = control.get('password');
  const repeatPassword = control.get('repeatPassword');

  if (password?.value !== repeatPassword?.value) {
    return { 'mismatch': true };
  }
  return null;
}