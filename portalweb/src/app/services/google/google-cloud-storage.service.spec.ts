import { TestBed } from '@angular/core/testing';

import { GoogleCloudStorageService } from './google-cloud-storage.service';

describe('GoogleCloudStorageService', () => {
  let service: GoogleCloudStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GoogleCloudStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
