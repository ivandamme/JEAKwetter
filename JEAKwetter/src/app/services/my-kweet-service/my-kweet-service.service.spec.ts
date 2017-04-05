import { TestBed, inject } from '@angular/core/testing';

import { MyKweetService } from './my-kweet-service.service';

describe('MyKweetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyKweetService]
    });
  });

  it('should ...', inject([MyKweetService], (service: MyKweetService) => {
    expect(service).toBeTruthy();
  }));
});
