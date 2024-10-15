import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ExerciseComponent } from '../../../components/training-plan/exercise/exercise.component';
import { ExerciseInfoModalComponent } from '../../../components/training-plan/exercise-info-modal/exercise-info-modal.component';
import { ITrainingPlanExercise } from '../../../models/interfaces';
import { TrainingPlanService } from '../../../services/trainingPlan/training-plan.service'; // Importamos el servicio

@Component({
  selector: 'app-exercise-guide-view',
  standalone: true,
  imports: [NgFor, NgIf, ExerciseComponent, ExerciseInfoModalComponent],
  templateUrl: './exercise-guide-view.component.html',
  styleUrls: ['./exercise-guide-view.component.css']
})
export class ExerciseGuideViewComponent implements OnInit {

  exercises: ITrainingPlanExercise[] = []; // Arreglo de ejercicios
  selectedExercise: ITrainingPlanExercise | null = null; // Para seleccionar un ejercicio y mostrar su detalle
  loading: boolean = true; // Para mostrar una indicación de carga

  constructor(private trainingPlanService: TrainingPlanService) { }

  ngOnInit(): void {
    this.getAllExercises(); // Obtener los ejercicios al iniciar el componente
  }

  // Método para obtener todos los ejercicios desde el servicio
  getAllExercises() {
    this.trainingPlanService.getAllExercises().subscribe({
      next: (response: ITrainingPlanExercise[]) => {
        this.exercises = response;
        this.loading = false; // Se deja de mostrar la carga
      },
      error: (error) => {
        console.error("Error al obtener los ejercicios", error);
        this.loading = false; // Incluso en caso de error, se deja de mostrar la carga
        // Aquí podrías agregar un mensaje de error para mostrar en la interfaz
      }
    });
  }

  // Método para seleccionar un ejercicio y mostrar el modal
  selectExercise(exercise: ITrainingPlanExercise) {
    this.selectedExercise = exercise; // Selecciona el ejercicio para mostrar los detalles
  }

  // Método para cerrar el modal
  closeModal() {
    this.selectedExercise = null; // Cierra el modal al reiniciar el valor de selectedExercise
  }
}
