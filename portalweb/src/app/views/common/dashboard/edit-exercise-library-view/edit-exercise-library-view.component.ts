import { Component } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl } from '@angular/forms';

interface Exercise {
  id: number; // Agrega un ID
  name: string;
  description: string;
}

@Component({
  selector: 'app-edit-exercise-library-view',
  standalone: true,
  imports: [NgIf, NgFor, ReactiveFormsModule, FormsModule],
  templateUrl: './edit-exercise-library-view.component.html',
  styleUrls: ['./edit-exercise-library-view.component.css']
})
export class EditExerciseLibraryViewComponent {
  showAddExerciseForm: boolean = false;
  newExercise: Omit<Exercise, 'id'> = { name: '', description: '' }; // Elimina 'id' del formulario
  exerciseList: Exercise[] = []; // Cambia el tipo según tu modelo
  nextId: number = 1; // Para generar IDs únicos
  filters: FormGroup = new FormGroup({
    search: new FormControl(''),
  });
  requestEnd: boolean = true; // Establece este valor según tu lógica

  addExercise() {
    const exerciseToAdd: Exercise = { id: this.nextId++, ...this.newExercise }; // Genera un ID único
    this.exerciseList.push(exerciseToAdd);
    this.newExercise = { name: '', description: '' }; // Limpiar el formulario
    this.showAddExerciseForm = false; // Ocultar formulario
  }

  filter() {
    // Implementa la lógica para filtrar ejercicios
  }

  openAddExerciseForm() {
    this.showAddExerciseForm = true;
  }

  closeAddExerciseForm() {
    this.showAddExerciseForm = false;
  }

  editExercise(exercise: Exercise) {
    // Implementa la lógica para editar un ejercicio
  }
}
