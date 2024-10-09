import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationOptionsViewComponent } from './notification-options-view.component';

describe('NotificationOptionsViewComponent', () => {
  let component: NotificationOptionsViewComponent;
  let fixture: ComponentFixture<NotificationOptionsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationOptionsViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotificationOptionsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
