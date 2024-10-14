import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../services/jwt/jwt.service';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { IDecodedAccessTokenInfo } from '../../models/interfaces';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-objectives-form',
  standalone: true,
  imports: [],
  templateUrl: './objectives-form.component.html',
  styleUrl: './objectives-form.component.css'
})
export class ObjectivesFormComponent {
  public token!: IDecodedAccessTokenInfo;
  constructor(private jwtService: JwtService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private storage: SessionStorageService){
      if(isPlatformBrowser(this.platformId)) 
        this.token = this.jwtService.getDecodedAccessToken(this.storage.getToken() as string);
  }

  onKeyDowninputNumberNoDecimal(event:any){
    const regex = /^[0-9]$/;
    const allowedKeys = ['Backspace', 'ArrowUp', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'Delete', 'Tab'];
    if(!regex.test(event.key) && !allowedKeys.includes(event.key)) 
      event.preventDefault();
  }

}
