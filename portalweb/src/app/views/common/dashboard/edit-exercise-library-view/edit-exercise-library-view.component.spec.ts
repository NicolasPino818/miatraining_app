import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { EditExerciseLibraryViewComponent } from './edit-exercise-library-view.component';

describe('EditExerciseLibraryViewComponent', () => {
  let component: EditExerciseLibraryViewComponent;
  let fixture: ComponentFixture<EditExerciseLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [EditExerciseLibraryViewComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(EditExerciseLibraryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add exercise', () => {
    const newExercise = { name: 'Ejercicio 1', description: 'Descripción 1' };
    component.addExercise(newExercise);
    expect(component.exerciseList.length).toBe(1);
    expect(component.exerciseList[0].name).toBe('Ejercicio 1');
  });
});
