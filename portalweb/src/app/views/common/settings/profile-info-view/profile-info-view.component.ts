import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../../../services/jwt/jwt.service';
import { SessionStorageService } from '../../../../services/storage/session-storage.service';
import { isPlatformBrowser, NgFor, NgIf } from '@angular/common';
import { UserProfileService } from '../../../../services/users/user-profile.service';
import { IProfileInfo } from '../../../../models/interfaces';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-profile-info-view',
  standalone: true,
  imports: [NgFor,NgIf,RouterLink],
  templateUrl: './profile-info-view.component.html',
  styleUrl: './profile-info-view.component.css'
})
export class ProfileInfoViewComponent implements OnInit{

  currentUserEmail!: string;
  profileInformation!: IProfileInfo;

  constructor(private jwtService: JwtService,
  @Inject(PLATFORM_ID) private platformId: Object,
  private storage: SessionStorageService,
  private profileService: UserProfileService){
    if(isPlatformBrowser(this.platformId)){
      let token = this.storage.getToken();
      this.currentUserEmail = this.jwtService.getDecodedAccessToken(token as string).sub;
    }
  }

  ngOnInit(): void {
    this.getProfileInfo();
  }

  getProfileInfo(){
    this.profileService.getProfileInfo(this.currentUserEmail).subscribe((response)=>{
      this.profileService.userProfile$.next(response);
      this.profileInformation = response;
    });
  }
}
