import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditExerciseLibraryViewComponent } from './edit-exercise-library-view.component';

describe('EditExerciseLibraryViewComponent', () => {
  let component: EditExerciseLibraryViewComponent;
  let fixture: ComponentFixture<EditExerciseLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditExerciseLibraryViewComponent]
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
