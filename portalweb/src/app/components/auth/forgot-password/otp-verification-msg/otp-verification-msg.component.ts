import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-otp-verification-msg',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './otp-verification-msg.component.html',
  styleUrl: './otp-verification-msg.component.css'
})
export class OtpVerificationMsgComponent {
  @Input()
  public success!: boolean;
  @Input()
  public finalMessage!: string;

  public isSuccess():boolean{return this.success;}
  public getFinalMessage(): string{return this.finalMessage;}
}
