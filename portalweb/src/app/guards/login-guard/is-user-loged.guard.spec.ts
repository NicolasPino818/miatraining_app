import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { isUserLogedGuard } from './is-user-loged.guard';

describe('isUserLogedGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => isUserLogedGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
