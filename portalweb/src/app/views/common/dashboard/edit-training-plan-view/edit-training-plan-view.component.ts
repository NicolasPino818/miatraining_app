import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { TrainingPlanService } from '../../../../services/trainingPlan/training-plan.service';
import { ITrainingPlan } from '../../../../models/interfaces';
import { catchError, EMPTY } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-edit-training-plan-view',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './edit-training-plan-view.component.html',
  styleUrl: './edit-training-plan-view.component.css'
})
export class EditTrainingPlanViewComponent implements OnInit {
  public trainingPlan!: ITrainingPlan;
  private planID!: number;

  public loadingPlan: boolean = false;

  constructor(
    private trainingPlanService: TrainingPlanService, 
    private activeRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.planID = this.activeRoute.snapshot.params['planID'] as number;
    this.getTrainingPlan();
  }

  getTrainingPlan() {
    this.loadingPlan = true;
    if (this.planID) {
      this.trainingPlanService.getPlanByPlanID(this.planID)
        .pipe(
          catchError((error: HttpErrorResponse) => {
            if (error.error.code === 'resource:not-found' && error.status === 404) {
              alert('El plan de entrenamiento buscado no existe.');
              const previousUrl = this.router.url.split('/').slice(0, -1).join('/');
              this.router.navigate([previousUrl]);
            }else{
              alert("hemos experimentado problemas para obtener el plan de entrenamiento.");
            }
            this.loadingPlan = false;
            return EMPTY;
          })
        )
        .subscribe((plan) => {
          this.trainingPlan = plan;
          this.orderDayByDayNumberAsc();
          this.loadingPlan = false;
        });
    }
  }

  orderDayByDayNumberAsc() {
    this.trainingPlan.planDays = this.trainingPlan.planDays.sort((a, b) => a.dayNumber - b.dayNumber);
  }

  selectDayName(dayNumber: number): string {
    switch (dayNumber) {
      case 1: return 'Lunes';
      case 2: return 'Martes';
      case 3: return 'Miércoles';
      case 4: return 'Jueves';
      case 5: return 'Viernes';
      case 6: return 'Sábado';
      case 7: return 'Domingo';
    }
    return 'SIN INFORMACIÓN';
  }
}
