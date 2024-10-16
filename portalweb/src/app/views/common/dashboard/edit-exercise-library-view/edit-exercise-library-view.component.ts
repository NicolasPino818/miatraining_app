import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AddExerciseModalComponent } from '../add-exercise-modal/add-exercise-modal.component';

interface Exercise {
  id: number;
  name: string;
  description: string;
  imageLink?: string;
}

@Component({
  selector: 'app-edit-exercise-library-view',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule, AddExerciseModalComponent],
  templateUrl: './edit-exercise-library-view.component.html',
  styleUrls: ['./edit-exercise-library-view.component.css']
})
export class EditExerciseLibraryViewComponent {
  showAddExerciseModal: boolean = false;
  exerciseList: Exercise[] = [];
  nextId: number = 1;
  selectedExercise: Exercise | null = null;

  addExercise(exercise: Omit<Exercise, 'id'>) {
    const exerciseToAdd: Exercise = { id: this.nextId++, ...exercise };
    this.exerciseList.push(exerciseToAdd);
    this.showAddExerciseModal = false;
  }

  onExerciseAdded(exercise: Omit<Exercise, 'id'> | null) {
    if (exercise) {
      this.addExercise(exercise);
    }
  }

  openAddExerciseForm() {
    this.showAddExerciseModal = true;
  }

  closeAddExerciseForm() {
    this.showAddExerciseModal = false;
  }

  selectExercise(exercise: Exercise) {
    this.selectedExercise = exercise;
    // Aquí puedes agregar la lógica para mostrar el modal de detalles si lo necesitas
  }
}
