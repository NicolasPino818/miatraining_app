import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser, NgFor, NgIf } from '@angular/common';
import { ExerciseComponent } from '../../../components/training-plan/exercise/exercise.component';
import { ITrainingPlan, ITrainingPlanDay, ITrainingPlanExercise } from '../../../models/interfaces';
import { ExerciseInfoModalComponent } from '../../../components/training-plan/exercise-info-modal/exercise-info-modal.component';
import { SessionStorageService } from '../../../services/storage/session-storage.service';
import { TrainingPlanService } from '../../../services/trainingPlan/training-plan.service';
import { JwtService } from '../../../services/jwt/jwt.service';

@Component({
  selector: 'app-client-training-plan-view',
  standalone: true,
  imports: [NgFor,NgIf, ExerciseComponent, ExerciseInfoModalComponent],
  templateUrl: './client-training-plan-view.component.html',
  styleUrls: ['./client-training-plan-view.component.css']
})
export class ClientTrainingPlanViewComponent implements OnInit{

  showExerciseModal:boolean = false;
  selectedExercise!: ITrainingPlanExercise | null;
  trainingPlan!: ITrainingPlan;
  today: Date = new Date();
  exercises: ITrainingPlanExercise[] = [];
  selectedDay!: string;
  currentUserEmail!: string;
  loadingPlan: boolean = true;
  constructor(private jwtService: JwtService,
    @Inject(PLATFORM_ID) private platformId: Object,
    private storage: SessionStorageService, 
    private trainingPlanService: TrainingPlanService){
    if(isPlatformBrowser(this.platformId)){
      let token = this.storage.getToken();
      this.currentUserEmail = this.jwtService.getDecodedAccessToken(token as string).sub;
    }
  }

  ngOnInit(): void {
    if(isPlatformBrowser(this.platformId)){
      this.getTrainingPlan();
    }
  }

  getTrainingPlan(){
    this.trainingPlanService.getPlanByEmail(this.currentUserEmail).subscribe((response)=>{
      if(response){
        this.trainingPlan = response;
        
        this.selectTrainingDay(this.today.getDay());
      }
      this.loadingPlan = false;
    });
  }

  selectExercise(id:number){
    const found:ITrainingPlanExercise | undefined = this.exercises.find((element:ITrainingPlanExercise) => {
      return element.exerciseID === id
    });
    if(found) {
      this.selectedExercise = found;
      this.showExerciseModal = true;
    }
  }

  selectTrainingDay(dayNumber: number){
    if (dayNumber === 0) dayNumber = 7;
    const day: ITrainingPlanDay | undefined = this.trainingPlan.planDays.find((element:ITrainingPlanDay) => {
      return element.dayNumber === dayNumber
    });
    if(day){
      this.selectDayName(dayNumber);
      this.exercises = day.exercises;
    }else{
      this.exercises = [];
    }
  }

  selectDayName(dayNumber: number){
    switch (dayNumber){
      case 1:
        this.selectedDay = 'Lunes';
        break;
      case 2:
        this.selectedDay = 'Martes';
        break;
      case 3:
        this.selectedDay = 'Miércoles';
        break;
      case 4:
        this.selectedDay = 'Jueves';
        break;
      case 5:
        this.selectedDay = 'Viernes';
        break;
      case 6:
        this.selectedDay = 'Sábado';
        break;
      case 7:
        this.selectedDay = 'Domingo';
        break;
    }
  }

  closeModal(){
    this.showExerciseModal = false;
  }

}
