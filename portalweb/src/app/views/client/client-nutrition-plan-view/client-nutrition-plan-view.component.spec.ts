import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientNutritionPlanComponent } from './client-nutrition-plan-view.component';

describe('ClientNutritionPlanComponent', () => {
  let component: ClientNutritionPlanComponent;
  let fixture: ComponentFixture<ClientNutritionPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientNutritionPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientNutritionPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
