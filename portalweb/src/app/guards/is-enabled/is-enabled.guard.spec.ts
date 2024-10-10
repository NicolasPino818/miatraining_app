import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { isEnabledGuard } from './is-enabled.guard';

describe('isEnabledGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => isEnabledGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
