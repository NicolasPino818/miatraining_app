import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTrainingPlanViewComponent } from './edit-training-plan-view.component';

describe('EditTrainingPlanViewComponent', () => {
  let component: EditTrainingPlanViewComponent;
  let fixture: ComponentFixture<EditTrainingPlanViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditTrainingPlanViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTrainingPlanViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
