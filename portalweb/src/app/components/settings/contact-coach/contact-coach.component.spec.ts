import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactCoachComponent } from './contact-coach.component';

describe('ContactCoachComponent', () => {
  let component: ContactCoachComponent;
  let fixture: ComponentFixture<ContactCoachComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactCoachComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactCoachComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
