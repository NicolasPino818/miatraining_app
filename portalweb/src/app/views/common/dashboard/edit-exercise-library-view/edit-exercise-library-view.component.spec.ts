import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms'; // Asegúrate de importar esto
import { EditExerciseLibraryViewComponent } from './edit-exercise-library-view.component';

describe('EditExerciseLibraryViewComponent', () => {
  let component: EditExerciseLibraryViewComponent;
  let fixture: ComponentFixture<EditExerciseLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule], // Agrega FormsModule aquí
      declarations: [EditExerciseLibraryViewComponent] // Declara el componente aquí
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditExerciseLibraryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
