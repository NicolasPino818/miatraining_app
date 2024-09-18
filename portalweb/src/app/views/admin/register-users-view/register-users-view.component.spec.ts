import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterUsersViewComponent } from './register-users-view.component';

describe('RegisterUsersViewComponent', () => {
  let component: RegisterUsersViewComponent;
  let fixture: ComponentFixture<RegisterUsersViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterUsersViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterUsersViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
