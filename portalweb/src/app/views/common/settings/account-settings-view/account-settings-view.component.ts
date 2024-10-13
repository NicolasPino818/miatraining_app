import { Component, OnInit } from '@angular/core';
import { NgFor } from '@angular/common';
import { accountOptions, userURLs } from '../../../../models/nav';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-account-settings-view',
  standalone: true,
  imports: [NgFor,RouterLink],
  templateUrl: './account-settings-view.component.html',
  styleUrls: ['./account-settings-view.component.css']
})
export class AccountSettingsViewComponent  implements OnInit {
  private urlObj!: string[];
  accountOptions!: any;
  public urlCurrentView!: string;
  constructor(private router: Router){ 
    this.urlObj = this.router.url.split('/');
    this.urlCurrentView = this.urlObj[2];
  }
  

  ngOnInit(): void {
    if(this.urlCurrentView === userURLs.adminBaseUrl){
      this.accountOptions = accountOptions.adminOptions;
    }else if(this.urlCurrentView === userURLs.clientBaseUrl){
      this.accountOptions = accountOptions.clientOptions;
    }
  }
  
}
