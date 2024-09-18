import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OtpVerificationMsgComponent } from './otp-verification-msg.component';

describe('OtpVerificationMsgComponent', () => {
  let component: OtpVerificationMsgComponent;
  let fixture: ComponentFixture<OtpVerificationMsgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OtpVerificationMsgComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OtpVerificationMsgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
