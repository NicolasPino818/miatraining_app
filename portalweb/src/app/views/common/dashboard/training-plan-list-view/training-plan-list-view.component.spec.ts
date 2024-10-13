import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingPlanListViewComponent } from './training-plan-list-view.component';

describe('TrainingPlanListViewComponent', () => {
  let component: TrainingPlanListViewComponent;
  let fixture: ComponentFixture<TrainingPlanListViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainingPlanListViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainingPlanListViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
