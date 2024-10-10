import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserConfigurationModalComponent } from './user-configuration-modal.component';

describe('UserConfigurationModalComponent', () => {
  let component: UserConfigurationModalComponent;
  let fixture: ComponentFixture<UserConfigurationModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserConfigurationModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserConfigurationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
