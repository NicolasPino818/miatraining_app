import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';

@Component({
  selector: 'app-exercise-info-modal',
  standalone: true,
  imports: [],
  templateUrl: './exercise-info-modal.component.html',
  styleUrl: './exercise-info-modal.component.css'
})
export class ExerciseInfoModalComponent {
  @Input()
  exercise!:IExercise|null;
  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  closeModalEvent(){
    this.closeModal.emit();
  }
}
