import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactCoachViewComponent } from './contact-coach-view.component';

describe('ContactCoachViewComponent', () => {
  let component: ContactCoachViewComponent;
  let fixture: ComponentFixture<ContactCoachViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactCoachViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactCoachViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
