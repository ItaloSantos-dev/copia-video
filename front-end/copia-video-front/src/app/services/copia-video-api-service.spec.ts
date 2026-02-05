import { TestBed } from '@angular/core/testing';

import { CopiaVideoApiService } from './copia-video-api-service';

describe('CopiaVideoApiService', () => {
  let service: CopiaVideoApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CopiaVideoApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
