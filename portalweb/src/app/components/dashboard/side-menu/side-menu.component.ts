import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { navLinks } from '../../../models/nav';

@Component({
  selector: 'app-side-menu',
  standalone: true,
  imports: [RouterLink, NgIf, NgFor,RouterLinkActive],
  templateUrl: './side-menu.component.html',
  styleUrl: './side-menu.component.css'
})
export class SideMenuComponent implements OnInit{
  navOpen:boolean = false;
  private urlObj!: string[];
  navLinks: any;
  public urlCurrentView!: string;
  constructor(private router: Router){ 
    this.urlObj = this.router.url.split('/');
    this.urlCurrentView = this.urlObj[this.urlObj.length - 2]
  }

  ngOnInit(): void {
    if(this.urlCurrentView === 'admin'){
      this.navLinks = navLinks.adminNavLinks
    }else if(this.urlCurrentView === 'cliente'){
      this.navLinks = navLinks.clientNavLinks
    }
  }

  toggleNav(){
    this.navOpen = !this.navOpen;
  }

  closeNav(){
    this.navOpen = false;
  }
}
