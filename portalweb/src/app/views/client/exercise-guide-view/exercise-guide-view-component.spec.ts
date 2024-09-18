import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ExerciseGuideComponent } from './exercise-guide-view.component';

describe('ExerciseGuideComponent', () => {
  let component: ExerciseGuideComponent;
  let fixture: ComponentFixture<ExerciseGuideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExerciseGuideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciseGuideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
