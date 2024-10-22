import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditExerciseTutorialComponent } from './edit-exercise-tutorial.component';

describe('EditExerciseTutorialComponent', () => {
  let component: EditExerciseTutorialComponent;
  let fixture: ComponentFixture<EditExerciseTutorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditExerciseTutorialComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditExerciseTutorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
