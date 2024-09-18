import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForgotPasswordViewComponent } from './forgot-password-view.component';

describe('ForgotPasswordViewComponent', () => {
  let component: ForgotPasswordViewComponent;
  let fixture: ComponentFixture<ForgotPasswordViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForgotPasswordViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForgotPasswordViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
