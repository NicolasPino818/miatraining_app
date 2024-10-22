import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';

@Component({
  selector: 'app-edit-exercise-tutorial',
  standalone: true,
  imports: [NgIf],
  templateUrl: './edit-exercise-tutorial.component.html',
  styleUrl: './edit-exercise-tutorial.component.css'
})
export class EditExerciseTutorialComponent {
  @Input()
  exercise!: IExercise;
  @Output()
  loadExerciseInfo: EventEmitter<number> = new EventEmitter<number>();

  @Output()
  editExerciseInfo: EventEmitter<number> = new EventEmitter<number>();

  @Output()
  deleteExercise: EventEmitter<number> = new EventEmitter<number>();

  constructor(){}
  
  emitLoadExcersiceInfo(id:number){
    this.loadExerciseInfo.emit(id);
  }

  emitEditExcersiceInfo(id:number){
    this.editExerciseInfo.emit(id);
  }

  emitDeleteExercise(id:number){
    this.deleteExercise.emit(id);
  }
}
