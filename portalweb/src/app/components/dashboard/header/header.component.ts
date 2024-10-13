import { Component } from '@angular/core';
import { UserProfileService } from '../../../services/users/user-profile.service';
import { IProfileInfo } from '../../../models/interfaces';
import { Router, RouterLink } from '@angular/router';
import { userURLs } from '../../../models/nav';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  profileInformation!: IProfileInfo;
  constructor(private profileService: UserProfileService,private router:Router){
    this.profileService.userProfile$.subscribe((profileInfo)=>{
      if(profileInfo) this.profileInformation = profileInfo;
    });
  }

  goToprofile(){
    let url = this.router.url.split('/');
    let urlCurrentView = url[2];

    this.router.navigate(['/dashboard',urlCurrentView,userURLs.settingsBaseUrl,'mi-perfil']);

  }

}
