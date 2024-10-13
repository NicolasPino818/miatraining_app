import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotFoudViewComponent } from './not-found-view.component';

describe('NotFoudViewComponent', () => {
  let component: NotFoudViewComponent;
  let fixture: ComponentFixture<NotFoudViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotFoudViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotFoudViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
