import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientNutritionPlanViewComponent } from './client-nutrition-plan-view.component';

describe('ClientNutritionPlanComponent', () => {
  let component: ClientNutritionPlanViewComponent;
  let fixture: ComponentFixture<ClientNutritionPlanViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientNutritionPlanViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientNutritionPlanViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
