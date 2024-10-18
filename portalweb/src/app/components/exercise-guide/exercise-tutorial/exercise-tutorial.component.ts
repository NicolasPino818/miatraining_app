import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-exercise-tutorial',
  standalone: true,
  imports: [NgIf],
  templateUrl: './exercise-tutorial.component.html',
  styleUrl: './exercise-tutorial.component.css'
})
export class ExerciseTutorialComponent {

  @Input()
  exercise!: IExercise;
  @Output()
  loadExerciseInfo: EventEmitter<number> = new EventEmitter<number>();

  constructor(){}
  emitLoadExcersiceInfo(id:number){
    this.loadExerciseInfo.emit(id);
  }
}
