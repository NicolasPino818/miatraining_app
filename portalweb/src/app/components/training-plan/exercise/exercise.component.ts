import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IExercise } from '../../../models/interfaces';
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
  exercise!: IExercise;
  @Output()
  loadExcersiceInfo: EventEmitter<number> = new EventEmitter<number>();
  
  constructor(){}
  emitLoadExcersiceInfo(id:number){
    this.loadExcersiceInfo.emit(id);
  }

}
