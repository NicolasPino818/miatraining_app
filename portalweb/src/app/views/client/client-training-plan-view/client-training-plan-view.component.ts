import { Component } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { ExerciseComponent } from '../../../components/training-plan/exercise/exercise.component';
import { IExercise } from '../../../models/interfaces';
import { ExerciseInfoModalComponent } from '../../../components/training-plan/exercise-info-modal/exercise-info-modal.component';

@Component({
  selector: 'app-client-training-plan-view',
  standalone: true,
  imports: [NgFor,NgIf, ExerciseComponent, ExerciseInfoModalComponent],
  templateUrl: './client-training-plan-view.component.html',
  styleUrls: ['./client-training-plan-view.component.css']
})
export class ClientTrainingPlanViewComponent {

  showExerciseModal:boolean = false;

  selectedExercise!: IExercise | null;

  exercises:IExercise[] = [
    {
      id: 1,
      name: 'Press de Pecho Sentado en maquina',
    },
    {
      id: 2,
      name: 'Press de pecho con cables',
    },
    {
      id: 3,
      name: 'Vuelos con mancuerna inclinado',
    },
    {
      id: 4,
      name: 'Vuelos sentado en máquina',
    },
    {
      id: 5,
      name: 'Elevaciones laterales',
    },
    {
      id: 6,
      name: 'Press de hombro con mancuernas',
    },
    {
      id: 7,
      name: 'Press de pecho inclinado con cables',
    }
  ];

  selectExercise(id:number){
    const found:IExercise | undefined = this.exercises.find((element:IExercise) => {
      return element.id === id
    });
    if(found) {
      this.selectedExercise = found;
      this.showExerciseModal = true;
    }
  }

  closeModal(){
    this.showExerciseModal = false;
  }

}
