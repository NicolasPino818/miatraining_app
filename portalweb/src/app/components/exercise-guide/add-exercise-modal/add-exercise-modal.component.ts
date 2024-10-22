import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ExerciseService } from '../../../services/exercise/exercise.service';
import { IExercise } from '../../../models/interfaces';

@Component({
  selector: 'app-add-exercise-modal',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './add-exercise-modal.component.html',
  styleUrls: ['./add-exercise-modal.component.css']
})
export class AddExerciseModalComponent implements OnInit{

  @Input()
  exercise!: IExercise | null;

  @Output()
  closeModal: EventEmitter<void> = new EventEmitter<void>();

  @Output()
  submissionEnd: EventEmitter<void> = new EventEmitter<void>();

  exerciseForm!:FormGroup;

  private imageFile!: File | null;
  selectedCategories = [];

  constructor(private exerciseService: ExerciseService){}

  ngOnInit(): void {
    if(this.exercise && this.exercise.id){
      this.exerciseForm = new FormGroup({
        name: new FormControl(this.exercise.name, [Validators.required]),
        description: new FormControl(this.exercise.description, [Validators.maxLength(500)]),
        image: new FormControl(null),
        tutorialSrc: new FormControl(this.exercise.tutorialSrc),
        traningType: new FormControl(this.exercise.trainingType, [Validators.required]),
      });
    }else{
      this.exerciseForm = new FormGroup({
        name: new FormControl(null, [Validators.required]),
        description: new FormControl(null, [Validators.maxLength(500)]),
        image: new FormControl(null, [Validators.required]),
        tutorialSrc: new FormControl(null),
        traningType: new FormControl('Casa', [Validators.required]),
      });
    }
  }

  clearForm(){
    this.exerciseForm.get("name")?.setValue(null);
    this.exerciseForm.get("description")?.setValue(null);
    this.exerciseForm.get("image")?.setValue(null);
    this.exerciseForm.get("tutorialSrc")?.setValue(null);
    this.exerciseForm.get("traningType")?.setValue('Casa');
    this.imageFile = null;
    this.selectedCategories = [];
  }

  closeModalEvent(){
    this.closeModal.emit();
  }

  submissionEndEvent(){
    this.submissionEnd.emit();
  }
  
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.imageFile = file; // Almacena el archivo en una variable
    }
  }
  
  onSubmit(){
    if (this.exerciseForm.valid) {

      const formData = new FormData();
      formData.append('name', this.exerciseForm.get('name')?.value);
      if(this.exerciseForm.get('description')?.value != null) 
        formData.append('description', this.exerciseForm.get('description')?.value);
      if(this.exerciseForm.get('tutorialSrc')?.value != null) 
        formData.append('tutorialSrc', this.exerciseForm.get('tutorialSrc')?.value);
      formData.append('trainingType', this.exerciseForm.get('traningType')?.value);
      if(this.imageFile) formData.append('imageFile', this.imageFile);
      formData.append('exerciseCategories', JSON.stringify(this.selectedCategories));

      if(this.exercise){
        this.exerciseService.updateExercise(this.exercise.id as number, formData).subscribe(()=>{
          alert("Ejercicio actualizado");
          this.clearForm();
          this.submissionEndEvent();
        })
      }else{
        this.exerciseService.createExercise(formData).subscribe(()=>{
          alert("Ejercicio añadido");
          this.clearForm();
          this.submissionEndEvent();
        })
      }
    }
  }
}
