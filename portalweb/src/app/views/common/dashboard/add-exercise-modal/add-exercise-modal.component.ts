import { Component, EventEmitter, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-exercise-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <div class="modal">
      <form [formGroup]="exerciseForm" (ngSubmit)="onSubmit()">
        <label>
          Nombre:
          <input formControlName="name" required />
        </label>
        <label>
          Descripción:
          <input formControlName="description" required />
        </label>
        <button type="submit">Agregar Ejercicio</button>
        <button type="button" (click)="close()">Cerrar</button>
      </form>
    </div>
  `,
  styleUrls: ['./add-exercise-modal.component.css']
})
export class AddExerciseModalComponent {
  @Output() exerciseAdded = new EventEmitter<{ name: string; description: string }>();
  exerciseForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.exerciseForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.exerciseForm.valid) {
      this.exerciseAdded.emit(this.exerciseForm.value);
      this.exerciseForm.reset();
    }
  }

  close() {
    this.exerciseForm.reset();
  }
}
