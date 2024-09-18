import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterEmailFormComponent } from './enter-email-form.component';

describe('EnterEmailFormComponent', () => {
  let component: EnterEmailFormComponent;
  let fixture: ComponentFixture<EnterEmailFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnterEmailFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnterEmailFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
