import { Component } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { ITrainingPlanExercise } from '../../../models/interfaces';

@Component({
  selector: 'app-edit-training-plan',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './edit-training-plan.component.html',
  styleUrl: './edit-training-plan.component.css'
})
export class EditTrainingPlanComponent {
  showExerciseModal:boolean = false;

  selectedExercise!: any | null;

  days = ['Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo'];

  exercises: any[] = [
    { exerciseID: 1, exerciseName: 'Press de Pecho Sentado en máquina' },
    { exerciseID: 2, exerciseName: 'Press de pecho con cables' },
    { exerciseID: 3, exerciseName: 'Vuelos con mancuerna inclinado' },
    { exerciseID: 4, exerciseName: 'Vuelos sentado en máquina' },
    { exerciseID: 5, exerciseName: 'Elevaciones laterales' },
    { exerciseID: 6, exerciseName: 'Press de hombro con mancuernas' },
    { exerciseID: 7, exerciseName: 'Press de pecho inclinado con cables' }
  ];

  selectExercise(id: number) {
    const found: any | undefined = this.exercises.find(exercise => exercise.exerciseID === id);
    if (found) {
      this.selectedExercise = found;
      this.showExerciseModal = true;
    }
  }

  closeModal() {
    this.showExerciseModal = false;
  }

}
