import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AddExerciseModalComponent } from '../../../../components/exercise-guide/add-exercise-modal/add-exercise-modal.component';
import { IExercise, IExerciseFilters } from '../../../../models/interfaces';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpErrorResponse } from '@angular/common/http';
import { UserProfileService } from '../../../../services/users/user-profile.service';
import { ExerciseService } from '../../../../services/exercise/exercise.service';
import { catchError, EMPTY } from 'rxjs';
import { ExerciseTutorialModalComponent } from '../../../../components/exercise-guide/exercise-tutorial-modal/exercise-tutorial-modal.component';
import { EditExerciseTutorialComponent } from '../../../../components/exercise-guide/edit-exercise-tutorial/edit-exercise-tutorial.component';

@Component({
  selector: 'app-edit-exercise-library-view',
  standalone: true,
  imports: [
    ReactiveFormsModule, 
    FormsModule, 
    CommonModule, 
    AddExerciseModalComponent,
    EditExerciseTutorialComponent, 
    NgxPaginationModule,
    ExerciseTutorialModalComponent,
  ],
  templateUrl: './edit-exercise-library-view.component.html',
  styleUrls: ['./edit-exercise-library-view.component.css']
})
export class EditExerciseLibraryViewComponent {
  showAddExerciseModal: boolean = false;
  selectedExercise: IExercise | null = null;
  exercises: IExercise[] = [];
  showModal: boolean = false;
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
      this.firstQueryDone = true;
      if (exercisePage.exercises.length > 0) {
        exercisePage.exercises.forEach((exercise) => {
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
    });

  }

  orderByIdAsc(){
    this.exercises = this.exercises.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
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

  openAddExerciseForm() {
    this.selectedExercise = null;
    this.showAddExerciseModal = true;
  }

  closeAddExerciseForm() {
    this.selectedExercise = null;
    this.showAddExerciseModal = false;
  }

  closeTutorialModal(){
    this.selectedExercise = null;
    this.showModal = false;
  }

  submitEnd(){
    this.exercises = [];
    this.getExercises(0);
  }

  selectExercise(id: number) {
    this.selectedExercise = null;
    const found: IExercise | undefined = this.exercises.find((element: IExercise) => {
      return element.id === id;
    });

    if (found) {
      this.selectedExercise = found;
      this.showModal = true; 
    }
  }

  selectEditExercise(id: number) {
    this.selectedExercise = null;
    const found: IExercise | undefined = this.exercises.find((element: IExercise) => {
      return element.id === id;
    });

    if (found) {
      this.selectedExercise = found;
      this.showAddExerciseModal = true; 
    }
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

  deleteExercise(id:number){
    if(confirm("¿Estás seguro de eliminar este ejercicio?, esta acción eliminará todas las rutinas asociadas a este ejercicio")){
      this.exerciseService.deleteExercise(id).subscribe(()=>{
        console.log("eliminado")
        this.exercises = [];
        this.getExercises(0);
      });
    }
  }
}
