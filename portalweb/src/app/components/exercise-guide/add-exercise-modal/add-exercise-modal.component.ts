import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-exercise-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-exercise-modal.component.html',
  styleUrls: ['./add-exercise-modal.component.css']
})
export class AddExerciseModalComponent {

  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  exerciseForm:FormGroup = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    description: new FormControl(null),
    image: new FormControl(null, [Validators.required]),
    tutorialLink: new FormControl(null),
    traningType: new FormControl('Casa', [Validators.required]),
  });

  selectedCategories = [];

  closeModalEvent(){
    this.closeModal.emit();
  }

  onSubmit(){

  }
}
