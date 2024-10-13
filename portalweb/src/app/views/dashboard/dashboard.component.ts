import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { SideMenuComponent } from '../../components/dashboard/side-menu/side-menu.component';
import { HeaderComponent } from '../../components/dashboard/header/header.component';
import { RouterOutlet } from '@angular/router';
import { JwtService } from '../../services/jwt/jwt.service';
import { SessionStorageService } from '../../services/storage/session-storage.service';
import { UserProfileService } from '../../services/users/user-profile.service';
import { isPlatformBrowser } from '@angular/common';
import { catchError, EMPTY } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet,HeaderComponent, SideMenuComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  currentUserEmail!: string;
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
    this.profileService.getProfileInfo(this.currentUserEmail)
    .pipe(
      catchError((error:HttpErrorResponse)=>{
        if (error.status === 0 && error.error.message === 'Failed to fetch') 
          alert("Estamos experimentando problemas para comunicarnos con el servidor");
        return EMPTY;
      })
    ).subscribe((response)=>{
      this.profileService.userProfile$.next(response);
    });
  }

}
