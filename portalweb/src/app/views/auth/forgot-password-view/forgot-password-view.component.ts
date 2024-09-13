import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { EnterEmailFormComponent } from '../../../components/auth/forgot-password/enter-email-form/enter-email-form.component';
import { OtpVerificationMsgComponent } from '../../../components/auth/forgot-password/otp-verification-msg/otp-verification-msg.component';
import { NewPasswordFormComponent } from '../../../components/auth/forgot-password/new-password-form/new-password-form.component';

@Component({
  selector: 'app-forgot-password-view',
  standalone: true,
  imports: [NgIf,EnterEmailFormComponent, NewPasswordFormComponent, OtpVerificationMsgComponent],
  templateUrl: './forgot-password-view.component.html',
  styleUrl: './forgot-password-view.component.css'
})
export class ForgotPasswordViewComponent {
  private stepOneSuccessful: boolean = false;
  private stepTwoSuccessful: boolean = false;
  private stepTwoFinish: boolean = false;

  private finalMessage!: string;

  private email!: string;

  public getEmail():string{return this.email;}
  public getFinalMessage():string{return this.finalMessage;}

  public stepOneEventHandler(stepOneData: {email:string, success:boolean}){
    this.stepOneSuccessful = stepOneData.success;
    this.email = stepOneData.email;
  }

  public stepTwoEventHandler(stepTwoData: {success:boolean, statusCode: number, message: string}){
    this.stepTwoSuccessful = stepTwoData.success;
    this.finalMessage = stepTwoData.message;
    this.stepTwoFinish = true;
  } 

  isStepOneSuccessful():boolean{return this.stepOneSuccessful;}
  isStepTwoSuccessful():boolean{return this.stepTwoSuccessful;}
  isStepTwoFinish():boolean{return this.stepTwoFinish;}
}
