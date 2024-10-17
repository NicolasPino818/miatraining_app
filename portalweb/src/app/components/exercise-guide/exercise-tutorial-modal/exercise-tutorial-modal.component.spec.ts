import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciseTutorialModalComponent } from './exercise-tutorial-modal.component';

describe('ExerciseTutorialModalComponent', () => {
  let component: ExerciseTutorialModalComponent;
  let fixture: ComponentFixture<ExerciseTutorialModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExerciseTutorialModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExerciseTutorialModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
