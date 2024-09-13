import { Component } from '@angular/core';
import { SideMenuComponent } from '../../components/dashboard/side-menu/side-menu.component';
import { HeaderComponent } from '../../components/dashboard/header/header.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet,HeaderComponent, SideMenuComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
