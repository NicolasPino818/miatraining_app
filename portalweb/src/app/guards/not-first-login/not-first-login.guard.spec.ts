import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { notFirstLoginGuard } from './not-first-login.guard';

describe('notFirstLoginGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => notFirstLoginGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
