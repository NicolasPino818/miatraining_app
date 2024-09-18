import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProgressTrackingComponent } from './progress-tracking-view.component';

describe('ProgressTrackingComponent', () => {
  let component: ProgressTrackingComponent;
  let fixture: ComponentFixture<ProgressTrackingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProgressTrackingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgressTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
