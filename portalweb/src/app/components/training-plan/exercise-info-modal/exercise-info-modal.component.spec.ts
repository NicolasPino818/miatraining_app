import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciseInfoModalComponent } from './exercise-info-modal.component';

describe('ExerciseInfoModalComponent', () => {
  let component: ExerciseInfoModalComponent;
  let fixture: ComponentFixture<ExerciseInfoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExerciseInfoModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExerciseInfoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
