import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciseTutorialComponent } from './exercise-tutorial.component';

describe('ExerciseTutorialComponent', () => {
  let component: ExerciseTutorialComponent;
  let fixture: ComponentFixture<ExerciseTutorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExerciseTutorialComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExerciseTutorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
