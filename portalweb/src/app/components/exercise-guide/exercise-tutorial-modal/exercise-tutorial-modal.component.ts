import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';  
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-exercise-tutorial-modal',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './exercise-tutorial-modal.component.html',
  styleUrls: ['./exercise-tutorial-modal.component.css']
})
export class ExerciseTutorialModalComponent {

  @Input()
  exercise!: IExercise | null;  // Recibe el ejercicio seleccionado
  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  closeModalEvent(){
    this.closeModal.emit();
  }



}
