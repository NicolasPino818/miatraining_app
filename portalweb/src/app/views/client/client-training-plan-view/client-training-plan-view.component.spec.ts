import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientTrainingPlanViewComponent } from './client-training-plan-view.component';

describe('ClientTrainingPlanViewComponent', () => {
  let component: ClientTrainingPlanViewComponent;
  let fixture: ComponentFixture<ClientTrainingPlanViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientTrainingPlanViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientTrainingPlanViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
