import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { navLinks } from '../../../models/nav';

@Component({
  selector: 'app-side-menu',
  standalone: true,
  imports: [RouterLink, NgIf, NgFor],
  templateUrl: './side-menu.component.html',
  styleUrl: './side-menu.component.css'
})
export class SideMenuComponent {
  navOpen:boolean = false;

  navLinks = navLinks;
  constructor(){ }

  toggleNav(){
    this.navOpen = !this.navOpen;
  }

  closeNav(){
    this.navOpen = false;
  }
}
