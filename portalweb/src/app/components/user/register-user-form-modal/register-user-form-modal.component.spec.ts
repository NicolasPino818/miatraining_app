import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterUserFormModalComponent } from './register-user-form-modal.component';

describe('RegisterUserFormModalComponent', () => {
  let component: RegisterUserFormModalComponent;
  let fixture: ComponentFixture<RegisterUserFormModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterUserFormModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterUserFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
