import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { IExercise, IExerciseFilters } from '../../../models/interfaces';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserProfileService } from '../../../services/users/user-profile.service';
import { ExerciseService } from '../../../services/exercise/exercise.service';
import { catchError, EMPTY } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { ExerciseTutorialComponent } from '../../../components/exercise-guide/exercise-tutorial/exercise-tutorial.component';

@Component({
  selector: 'app-exercise-guide-view',
  standalone: true,
  imports: [NgFor, NgIf,NgxPaginationModule, ReactiveFormsModule, ExerciseTutorialComponent],
  templateUrl: './exercise-guide-view.component.html',
  styleUrls: ['./exercise-guide-view.component.css']
})
export class ExerciseGuideViewComponent {

  exercises: IExercise[] = []; // Arreglo de ejercicios
  selectedExercise: IExercise | null = null; // Para seleccionar un ejercicio y mostrar su detalle
  requestEnd: boolean = false;
  filters: IExerciseFilters ={
    categories: [],
    trainingTypes: [],
  };
  nextQueryPage: number = 0;
  lastQueryPage: number = 0;
  firstQueryDone: boolean = false;
  currentPaginationPage: number = 1;
  itemsPerPage: number = 10;
  totalPages!:number; 
  filtersForm: FormGroup = new FormGroup({
    search: new FormControl(''),
    category: new FormControl('ALL', [
      Validators.required
    ]),
    trainingType: new FormControl('ALL', [
      Validators.required
    ]),
  });

  constructor(private userProfileService: UserProfileService, private exerciseService: ExerciseService) {
    this.userProfileService.userProfile$.subscribe((profileInfo)=>{
      if (profileInfo) {
        this.getFilters();
      }
    })
  }

  getFilters(){
    this.exerciseService.getExerciseFilters().subscribe((filters)=>{
      this.filters = filters;
      this.getExercises(this.nextQueryPage);
    })
  }

  // Método para obtener todos los ejercicios desde el servicio
  getExercises(pageNumber: number) {
    let category = this.filtersForm.get('category')?.value as string;
    let trainingType = this.filtersForm.get('trainingType')?.value as string;
    let search = this.filtersForm.get('search')?.value as string;
    search = search.trim();

    if (this.exercises.length == 0) this.requestEnd = false;

    this.exerciseService.getExercisesPage({pageNumber: pageNumber}, {search,category,trainingType}).pipe(
      catchError((error:HttpErrorResponse)=>{
        this.catchError(error);
        return EMPTY;
      })
    ).subscribe((exercisePage) => {
      console.log(exercisePage);
      this.firstQueryDone = true;
      if (exercisePage.exercises.length > 0) {
        exercisePage.exercises.forEach(exercise => {
          // Evitar agregar duplicados
          if (!this.exercises.some(existing => existing.id === exercise.id)) {
            this.exercises.push(exercise);
          }
        });
        this.orderByIdAsc();
        // Actualizamos las páginas consultadas
        this.lastQueryPage = exercisePage.pageNumber;
        this.nextQueryPage = exercisePage.pageNumber + 1;
  
        // Actualizar el número total de páginas para la paginación
        this.totalPages = Math.ceil(this.exercises.length / this.itemsPerPage);
      }
      this.requestEnd = true;
      console.log(this.exercises);
    });

  }

  orderByIdAsc(){
    this.exercises = this.exercises.sort((a, b) => a.id - b.id);
  }

  onPageChange(page: number): void {
    this.currentPaginationPage = page;
    // Si estamos en la penúltima o última página visible, obtener más usuarios
    if (this.currentPaginationPage >= this.totalPages - 1) {
      this.getExercises(this.nextQueryPage);
    }
  }

  catchError(error:HttpErrorResponse){
    alert("Estamos experimentando problemas al cargar los ejercicios.");
  }

  onSubmit(){
    this.currentPaginationPage = 1;
    this.requestEnd = false;
    this.firstQueryDone = false;

    let category = this.filtersForm.get('category')?.value as string;
    let trainingType = this.filtersForm.get('trainingType')?.value as string;
    let search = this.filtersForm.get('search')?.value as string;
    search = search.trim();

    this.exerciseService.getExercisesPage({pageNumber: 0}, {search, category, trainingType})
    .pipe(
      catchError((error:HttpErrorResponse)=>{
        this.catchError(error);
        return EMPTY;
      })
    ).subscribe((exercisePage) => {
      this.exercises = exercisePage.exercises;
      this.orderByIdAsc();
      // Actualizamos las páginas consultadas
      this.lastQueryPage = 0;
      this.nextQueryPage = 1;

      // Actualizar el número total de páginas para la paginación
      this.totalPages = Math.ceil(this.exercises.length / this.itemsPerPage);
      this.requestEnd = true;
    });
  }
}
