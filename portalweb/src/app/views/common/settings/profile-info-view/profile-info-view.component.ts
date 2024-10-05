import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { JwtService } from '../../../../services/jwt/jwt.service';
import { SessionStorageService } from '../../../../services/storage/session-storage.service';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-profile-info-view',
  standalone: true,
  imports: [],
  templateUrl: './profile-info-view.component.html',
  styleUrl: './profile-info-view.component.css'
})
export class ProfileInfoViewComponent implements OnInit{
  constructor(private jwtService: JwtService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private storage: SessionStorageService){}

    ngOnInit(): void {
      if(isPlatformBrowser(this.platformId)){}
  }
}
