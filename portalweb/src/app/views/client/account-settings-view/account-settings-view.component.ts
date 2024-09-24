import { Component } from '@angular/core';
import { NgFor } from '@angular/common';
import { accountOptions } from '../../../models/nav';

@Component({
  selector: 'app-account-settings-view',
  standalone: true,
  imports: [NgFor],
  templateUrl: './account-settings-view.component.html',
  styleUrls: ['./account-settings-view.component.css']
})
export class AccountSettingsViewComponent {
  accountOptions = accountOptions.clientOptions;
}
