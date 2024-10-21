import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ITrainingPlanExercise } from '../../../models/interfaces';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-exercise',
  standalone: true,
  imports: [NgIf],
  templateUrl: './exercise.component.html',
  styleUrl: './exercise.component.css'
})
export class ExerciseComponent {

  @Input()
  exercise!: ITrainingPlanExercise;
  @Output()
  loadExerciseInfo: EventEmitter<number> = new EventEmitter<number>();

  constructor(){}
  emitLoadExcersiceInfo(id:number){
    this.loadExerciseInfo.emit(id);
  }

}
