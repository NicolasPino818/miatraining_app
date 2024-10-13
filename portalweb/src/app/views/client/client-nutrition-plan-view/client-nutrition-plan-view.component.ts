import { Component } from '@angular/core';

interface Food {
  name: string;
  amount: string;
  calories: number;
}

@Component({
  selector: 'app-client-nutrition-plan-view',
  standalone: true,
  imports: [],
  templateUrl: './client-nutrition-plan-view.component.html',
  styleUrl: './client-nutrition-plan-view.component.css'
})
export class ClientNutritionPlanViewComponent {

}
