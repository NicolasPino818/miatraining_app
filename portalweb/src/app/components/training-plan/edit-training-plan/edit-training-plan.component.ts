import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { ITrainingPlanExercise } from '../../../models/interfaces';
import { TrainingPlanService } from '../../../services/trainingPlan/training-plan.service';

@Component({
  selector: 'app-edit-training-plan',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './edit-training-plan.component.html',
  styleUrls: ['./edit-training-plan.component.css']
})
export class EditTrainingPlanComponent implements OnInit {

  showExerciseModal: boolean = false;
  selectedExercise!: ITrainingPlanExercise | null;
  plan: any; // Aquí almacenamos el plan que viene del backend

  // Aquí inyectas el servicio dentro del constructor
  constructor(private trainingPlanService: TrainingPlanService) {}

  // Implementa ngOnInit para ejecutar código cuando se carga el componente
  ngOnInit(): void {
    this.getExercises(); // Llama a la función que obtiene los ejercicios del backend
  }

  // Método que obtiene los ejercicios del backend
  getExercises(): void {
    const planID = 1; // Aquí puedes cambiar por un ID dinámico
    this.trainingPlanService.getPlanByPlanID(planID).subscribe(
      (plan) => {
        this.plan = plan; // Asignar el plan recibido a la propiedad
      },
      (error) => {
        console.error('Error al obtener el plan de entrenamiento:', error);
      }
    );
  }

  // Método para seleccionar un ejercicio específico
  selectExercise(id: number) {
    const found: ITrainingPlanExercise | undefined = this.plan?.planDays
      .flatMap((day: any) => day.routine || [])
      .find((exercise: ITrainingPlanExercise) => exercise.exerciseID === id); // <-- Definir el tipo del parámetro aquí

    if (found) {
      this.selectedExercise = found;
      this.showExerciseModal = true;
    }
  }

  closeModal() {
    this.showExerciseModal = false;
  }
}
