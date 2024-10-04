import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ITrainingPlanExercise } from '../../../models/interfaces';
import { NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-exercise-info-modal',
  standalone: true,
  imports: [NgFor,NgIf,RouterLink],
  templateUrl: './exercise-info-modal.component.html',
  styleUrl: './exercise-info-modal.component.css'
})
export class ExerciseInfoModalComponent {
  @Input()
  exercise!:ITrainingPlanExercise|null;
  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  closeModalEvent(){
    this.closeModal.emit();
  }
}
